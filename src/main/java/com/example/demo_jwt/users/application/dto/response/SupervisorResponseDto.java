package com.example.demo_jwt.users.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupervisorResponseDto {
    private Long id;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String username;
    private String department;
}
