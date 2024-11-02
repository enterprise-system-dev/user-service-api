package com.ecom.user_service_api.service.impl;

import com.ecom.user_service_api.config.KeycloakSecurityUtil;
import com.ecom.user_service_api.dto.request.RequestUserDto;
import com.ecom.user_service_api.dto.request.RequestUserLoginDto;
import com.ecom.user_service_api.entity.SystemUser;
import com.ecom.user_service_api.exception.DuplicateEntryException;
import com.ecom.user_service_api.repo.SystemUserRepo;
import com.ecom.user_service_api.service.SystemUserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final KeycloakSecurityUtil securityUtil;
    private final SystemUserRepo systemUserRepo;
    @Value("${keycloak.config.realm}")
    private String realm;

    @Value("${keycloak.config.client-id}")
    private String clientId;

    @Value("${keycloak.config.secret}")
    private String secret;

    @Value("${spring.security.oauth2.resourceserver.jwt.token-uri}")
    private String apiUrl;


    @Override
    public void signup(RequestUserDto dto) {

        String userId="";
        Keycloak keycloak=null;

        UserRepresentation representation= null;
        keycloak = securityUtil.getKeycloakInstance();

        representation =
                keycloak.realm(realm).users()
                .search(dto.getEmail()).stream().findFirst().orElse(null);

        if(representation!=null){
            throw new DuplicateEntryException("email is already exists");
        }

        UserRepresentation userR = convertUser(dto);
        Response response = keycloak.realm(realm).users().create(userR);

        if(response.getStatus()==Response.Status.CREATED.getStatusCode()){
            RoleRepresentation roleRepresentation = keycloak.realm(realm)
                    .roles().get("user").toRepresentation();
             userId =
                    response.getLocation().getPath().replaceAll(".*/([^/]+)$","$1");
             keycloak.realm(realm).users().get(userId).roles()
                     .realmLevel().add(Arrays.asList(roleRepresentation));

            SystemUser su = new SystemUser(
                    userId, dto.getEmail(), dto.getFName(), dto.getLName()
            );
            systemUserRepo.save(su);
        }

    }

    private UserRepresentation convertUser(RequestUserDto dto) {
        UserRepresentation userR = new UserRepresentation();
        userR.setUsername(dto.getEmail());
        userR.setFirstName(dto.getFName());
        userR.setLastName(dto.getLName());
        userR.setEnabled(true);
        userR.setEmailVerified(true);
        List<CredentialRepresentation> cre = new ArrayList<>();
        CredentialRepresentation r = new CredentialRepresentation();
        r.setTemporary(false);
        r.setValue(dto.getPassword());
        cre.add(r);
        userR.setCredentials(cre);
        return userR;
    }

    @Override
    public Object login(RequestUserLoginDto dto) {
        return null;
    }
}
