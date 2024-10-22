package com.example.demo_jwt.trips.domain.service;

import com.example.demo_jwt.trips.application.dto.request.TripRegisterDto;
import com.example.demo_jwt.trips.application.dto.response.TripCreatedDto;
import com.example.demo_jwt.trips.application.dto.response.TripInformationDto;
import com.example.demo_jwt.trips.domain.enums.Status;

import java.util.List;
import java.util.Optional;

public interface TripService {

    Optional<TripInformationDto> findById(Long id);
    List<TripInformationDto> findAll();
    boolean existsById(Long id);
    TripCreatedDto save(TripRegisterDto tripRegisterDto);
    void delete(Long id);
}