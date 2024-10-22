package com.example.demo_jwt.trips.application.dto.response;

public record TripInformationDto(
        String driverId,
        String driverName,
        String origin,
        String destination,
        String startTime,
        String endTime,
        String status
) {
}
