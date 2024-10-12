package com.example.demo_jwt.users.application.dto.response;

import lombok.Data;

@Data
public abstract class DriverProfile {
    private Long id;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String phone;

}
