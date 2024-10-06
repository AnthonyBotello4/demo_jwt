package com.example.demo_jwt.security.service;

import com.example.demo_jwt.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public String authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        System.out.println("User authenticated: " + username);

        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("User details: " + userDetails.getUsername() + " - " + userDetails.getPassword());
            return jwtUtil.generateToken(userDetails.getUsername());
        } catch (Exception e) {
            System.out.println("User not found: " + username);
        }

        return null;
    }
}