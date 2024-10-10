package com.example.demo_jwt.users.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupervisorRegisterDto {
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String username;
    private String password;
    private String department;
}
