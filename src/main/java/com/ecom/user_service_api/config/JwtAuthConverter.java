package com.ecom.user_service_api.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> grantedAuthorities = extractGrantedAuthorities(source);
        return new JwtAuthenticationToken(source,grantedAuthorities);
    }
    private Collection<GrantedAuthority> extractGrantedAuthorities(Jwt jwt){
        if(jwt.getClaim("realm_access")!=null){
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            ObjectMapper mapper = new ObjectMapper();
            List<String> keyCloakRoles = mapper.convertValue(realmAccess.get("roles"),
                    new TypeReference<List<String>>() {
                    });
            List<GrantedAuthority> roles = new ArrayList<>();
            for(String r:keyCloakRoles){
                roles.add(new SimpleGrantedAuthority(r));
            }
            return roles;
        }
        return new ArrayList<>();
    }
}
