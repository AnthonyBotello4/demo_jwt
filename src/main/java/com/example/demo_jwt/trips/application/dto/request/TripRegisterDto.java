package com.example.demo_jwt.trips.application.dto.request;

public record TripRegisterDto(
        Long driverId,
        String origin,
        String destination,
        String startTime,
        String endTime) {
}
