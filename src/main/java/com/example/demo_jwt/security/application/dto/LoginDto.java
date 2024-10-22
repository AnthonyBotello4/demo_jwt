package com.example.demo_jwt.security.application.dto;

import com.example.demo_jwt.shared.utils.ValidationMessageConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginDto implements Serializable {
    @NotEmpty(message = ValidationMessageConstants.OBLIGATORY_FIELD)
    @NotBlank(message = ValidationMessageConstants.BLANK_FIELD)
    private String username;

    @NotEmpty(message = ValidationMessageConstants.OBLIGATORY_FIELD)
    @NotBlank(message = ValidationMessageConstants.BLANK_FIELD)
    private String password;

}
