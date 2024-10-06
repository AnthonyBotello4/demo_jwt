package com.example.demo_jwt.kernel.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestAuthentication {

    private String username;
    private String password;
}
