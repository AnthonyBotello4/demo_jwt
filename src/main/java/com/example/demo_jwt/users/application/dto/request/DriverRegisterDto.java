package com.example.demo_jwt.users.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverRegisterDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El primer apellido es obligatorio")
    private String firstLastName;

    @NotBlank(message = "El segundo apellido es obligatorio")
    private String secondLastName;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "La placa es obligatoria")
    private String plate;
}
