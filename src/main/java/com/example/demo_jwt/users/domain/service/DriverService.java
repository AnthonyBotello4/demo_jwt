package com.example.demo_jwt.users.domain.service;

import com.example.demo_jwt.users.application.dto.response.DriverPrivateProfileDto;
import com.example.demo_jwt.users.application.dto.response.DriverPublicProfileDto;
import com.example.demo_jwt.users.application.dto.request.DriverRegisterDto;
import com.example.demo_jwt.users.application.dto.response.DriverResponseDto;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    DriverResponseDto save(DriverRegisterDto driverRegisterDto);
    void delete(Long id);
    Optional<DriverResponseDto> findById(Long id);
    List<DriverResponseDto> findAll();
    List<DriverResponseDto> findAllByIdIn(List<Long> ids);

    Optional<DriverPrivateProfileDto> getPrivateProfile(Long id);
    Optional<DriverPublicProfileDto> getPublicProfile(Long id);

    boolean existsById(Long id);
}