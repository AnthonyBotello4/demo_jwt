package com.example.demo_jwt.users.domain.service;

import com.example.demo_jwt.users.application.dto.request.SupervisorRegisterDto;
import com.example.demo_jwt.users.application.dto.response.SupervisorResponseDto;

import java.util.List;
import java.util.Optional;

public interface SupervisorService {
    SupervisorResponseDto save(SupervisorRegisterDto supervisor);
    void delete(Long id);
    Optional<SupervisorResponseDto> findById(Long id);
    List<SupervisorResponseDto> findAll();

    boolean existsById(Long id);
}
