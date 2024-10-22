package com.example.demo_jwt.trips.domain.service;

import com.example.demo_jwt.trips.application.dto.response.TripInformationDto;

import java.util.List;

public interface TripStatusByUserService {
    List<TripInformationDto> findAllByStatus(Long statusId);
    List<TripInformationDto> findAllBySupervisorId(Long id);
    List<TripInformationDto> findAllByDriverId(Long id);
    List<TripInformationDto> findAllByDriverIdAndStatus(Long driverId, Long statusId);
    List<TripInformationDto> findAllBySupervisorIdAndStatus(Long supervisorId, Long statusId);
    List<TripInformationDto> findAllByDriverIdAndSupervisorId(Long driverId, Long supervisorId);
}
