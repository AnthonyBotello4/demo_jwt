package com.example.demo_jwt.users.application.mapper;

import com.example.demo_jwt.users.application.dto.request.DriverRegisterDto;
import com.example.demo_jwt.users.application.dto.response.DriverResponseDto;
import com.example.demo_jwt.users.domain.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mappings({
            @Mapping(target = "user.name", source = "name"),
            @Mapping(target = "user.firstLastName", source = "firstLastName"),
            @Mapping(target = "user.secondLastName", source = "secondLastName"),
            @Mapping(target = "user.email", source = "email"),
            @Mapping(target = "user.username", source = "username"),
            @Mapping(target = "user.password", source = "password"),
            @Mapping(target = "plate", source = "plate")
    })
    Driver registerDtoToDriver(DriverRegisterDto driver);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "firstLastName", source = "user.firstLastName"),
            @Mapping(target = "secondLastName", source = "user.secondLastName"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "plate", source = "plate")
    })
    DriverResponseDto driverToResponseDto(Driver driver);
}
