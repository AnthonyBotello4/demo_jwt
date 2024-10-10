package com.example.demo_jwt.security.interfaces.rest;

import com.example.demo_jwt.security.application.dto.LoginDto;
import com.example.demo_jwt.security.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        return authenticationService.authenticate(username, password);
    }
}