package com.example.demo_jwt.users.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DriverPrivateProfileDto extends DriverProfile {
    private String username;
    private String plate;
}
