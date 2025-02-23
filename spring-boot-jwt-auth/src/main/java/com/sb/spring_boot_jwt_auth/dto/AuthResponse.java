package com.sb.spring_boot_jwt_auth.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private Long expiresIn;
}