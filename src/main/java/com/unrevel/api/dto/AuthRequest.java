package com.unrevel.api.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
}
