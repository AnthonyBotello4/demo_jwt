package com.example.demo_jwt.trips.infrastructure.service;

import com.example.demo_jwt.trips.application.dto.response.TripInformationDto;
import com.example.demo_jwt.trips.application.mapper.TripMapper;
import com.example.demo_jwt.trips.domain.entity.Trip;
import com.example.demo_jwt.trips.domain.model.DriverDto;
import com.example.demo_jwt.trips.domain.service.TripStatusByUserService;
import com.example.demo_jwt.trips.infrastructure.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TripStatusByUserServiceImp implements TripStatusByUserService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    public TripStatusByUserServiceImp(TripRepository tripRepository, TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
    }

    /// ToDo
    @Override
    public List<TripInformationDto> findAllBySupervisorId(Long id) {
        return null;//userTripsApplicationService.findAllTripsBySupervisorId(id).stream().map(tripMapper::tripToTripInformationDto).toList();
    }

    @Override
    public List<TripInformationDto> findAllByDriverId(Long id) {
        List<Trip> trips = tripRepository.findAllByDriverId(id);
        return tripsAssembler(trips, List.of());
    }

    /// ToDo
    @Override
    public List<TripInformationDto> findAllByDriverIdAndStatus(Long driverId, Long statusId) {
        return List.of();
    }

    /// ToDo
    @Override
    public List<TripInformationDto> findAllBySupervisorIdAndStatus(Long supervisorId, Long statusId) {
        return List.of();
    }

    /// ToDo
    @Override
    public List<TripInformationDto> findAllByDriverIdAndSupervisorId(Long driverId, Long supervisorId) {
        return List.of();
    }

    @Override
    public List<TripInformationDto> findAllByStatus(Long statusId) {
        List<Trip> trips = tripRepository.findAllByStatus_Id(statusId);
        return tripsAssembler(trips, List.of());
    }

    private List<TripInformationDto> tripsAssembler(List<Trip> trips, List<DriverDto> drivers)  {
        return trips.stream()
                .map(trip -> {
                    DriverDto driverDto = drivers.stream()
                            .filter(driver -> driver.getId().equals(trip.getDriverId()))
                            .findFirst()
                            .orElse(new DriverDto());

                    return tripMapper.tripToTripInformationDto(trip, driverDto);

                }).collect(Collectors.toList());
    }
}
