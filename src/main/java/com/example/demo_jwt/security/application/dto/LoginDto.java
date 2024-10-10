package com.example.demo_jwt.security.application.dto;

import com.example.demo_jwt.shared.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginDto implements Serializable {
    @NotEmpty(message = Constants.OBLIGATORY_FIELD)
    @NotBlank(message = Constants.BLANK_FIELD)
    private String username;

    @NotEmpty(message = Constants.OBLIGATORY_FIELD)
    @NotBlank(message = Constants.BLANK_FIELD)
    private String password;

}
