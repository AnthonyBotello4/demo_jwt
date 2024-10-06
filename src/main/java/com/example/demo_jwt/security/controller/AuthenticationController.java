package com.example.demo_jwt.security.controller;

import com.example.demo_jwt.security.service.AuthenticationService;
import com.example.demo_jwt.security.entity.RequestAuthentication;
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
    public String createAuthenticationToken(@RequestBody RequestAuthentication requestAuthentication) {
        String username = requestAuthentication.getUsername();
        String password = requestAuthentication.getPassword();

        return authenticationService.authenticate(username, password);
    }
}
