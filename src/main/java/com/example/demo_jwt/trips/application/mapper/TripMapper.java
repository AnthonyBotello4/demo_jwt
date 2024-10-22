package com.example.demo_jwt.trips.application.mapper;

import com.example.demo_jwt.trips.application.dto.request.TripRegisterDto;
import com.example.demo_jwt.trips.application.dto.response.TripCreatedDto;
import com.example.demo_jwt.trips.application.dto.response.TripInformationDto;
import com.example.demo_jwt.trips.domain.entity.Trip;
import com.example.demo_jwt.trips.domain.model.DriverDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mappings({
            @Mapping(target = "driverId", source = "driverId"),
            @Mapping(target = "origin", source = "origin"),
            @Mapping(target = "destination", source = "destination"),
            @Mapping(target = "startTime", source = "startTime"),
            @Mapping(target = "endTime", source = "endTime"),
    })
    Trip registerDtoToTrip(TripRegisterDto tripRegisterDto);

    @Mappings({
            @Mapping(target = "id", source = "trip.id"),
            @Mapping(target = "driverId", source = "driverDto.id"),
            @Mapping(target = "driverName", expression = "java(driverDto.getName() + ' ' + driverDto.getFirstLastName() + ' ' + driverDto.getSecondLastName())"),
            @Mapping(target = "origin", source = "trip.origin"),
            @Mapping(target = "destination", source = "trip.destination"),
            @Mapping(target = "startTime", source = "trip.startTime"),
            @Mapping(target = "endTime", source = "trip.endTime"),
            @Mapping(target = "status", source = "trip.status.status")
    })
    TripCreatedDto tripToTripCreatedDto(Trip trip, DriverDto driverDto);

    @Mappings({
            @Mapping(target = "driverId", source = "driverDto.id"),
            @Mapping(target = "driverName", expression = "java(driverDto.getName() + ' ' + driverDto.getFirstLastName() + ' ' + driverDto.getSecondLastName())"),
            @Mapping(target = "origin", source = "trip.origin"),
            @Mapping(target = "destination", source = "trip.destination"),
            @Mapping(target = "startTime", source = "trip.startTime"),
            @Mapping(target = "endTime", source = "trip.endTime"),
            @Mapping(target = "status", source = "trip.status.status")
    })
    TripInformationDto tripToTripInformationDto(Trip trip, DriverDto driverDto);
}
