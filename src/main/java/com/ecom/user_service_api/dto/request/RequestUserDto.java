package com.ecom.user_service_api.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestUserDto {
    private String email;
    private String password;
    private String fName;
    private String lName;
}
