package com.ecom.user_service_api.api;

import com.ecom.user_service_api.dto.request.RequestUserDto;
import com.ecom.user_service_api.dto.request.RequestUserLoginDto;
import com.ecom.user_service_api.service.SystemUserService;
import com.ecom.user_service_api.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/api/v1/users")
@RequiredArgsConstructor
public class SystemUserController {
    private final SystemUserService systemUserService;

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> createUser(@RequestBody RequestUserDto dto){
        systemUserService.signup(dto);
        return new ResponseEntity<>(
                new StandardResponse(201,null,"User created"),
                HttpStatus.CREATED
        );
    }
    @PostMapping("/login")
    public ResponseEntity<StandardResponse> createUser(@RequestBody RequestUserLoginDto dto){
        return new ResponseEntity<>(
                new StandardResponse(200,systemUserService.login(dto),"User Logged"),
                HttpStatus.OK
        );
    }
}
