package com.ecom.user_service_api.service;

import com.ecom.user_service_api.dto.request.RequestUserDto;
import com.ecom.user_service_api.dto.request.RequestUserLoginDto;

public interface SystemUserService {
    public void signup(RequestUserDto dto);
    public Object login(RequestUserLoginDto dto);
}
