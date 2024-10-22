package com.example.demo_jwt.trips.application.dto.response;

import com.example.demo_jwt.trips.domain.enums.Status;

public record TripCreatedDto(
        Long id,
        String driverId,
        String driverName,
        String origin,
        String destination,
        String startTime,
        String endTime,
        Status status
) {
}
