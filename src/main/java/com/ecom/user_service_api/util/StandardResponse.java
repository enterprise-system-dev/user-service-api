package com.ecom.user_service_api.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardResponse {
    private int code;
    private Object data;
    private String message;
}
