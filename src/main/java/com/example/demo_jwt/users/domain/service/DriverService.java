package com.example.demo_jwt.users.domain.service;

import com.example.demo_jwt.users.application.dto.request.DriverRegisterDto;
import com.example.demo_jwt.users.application.dto.response.DriverResponseDto;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    DriverResponseDto save(DriverRegisterDto driverRegisterDto);
    void delete(Long id);
    Optional<DriverResponseDto> findById(Long id);
    List<DriverResponseDto> findAll();
}