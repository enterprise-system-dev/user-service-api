package com.ecom.user_service_api.service.impl;

import com.ecom.user_service_api.dto.request.RequestUserDto;
import com.ecom.user_service_api.dto.request.RequestUserLoginDto;
import com.ecom.user_service_api.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Override
    public void signup(RequestUserDto dto) {

    }

    @Override
    public Object login(RequestUserLoginDto dto) {
        return null;
    }
}
