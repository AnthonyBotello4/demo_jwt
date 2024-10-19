package com.example.demo_jwt.application;

import com.example.demo_jwt.trips.domain.entity.Trip;
import com.example.demo_jwt.trips.infrastructure.repository.TripRepository;
import com.example.demo_jwt.users.domain.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorTripsApplicationService {

    private final SupervisorRepository supervisorRepository;
    private final TripRepository tripRepository;

    @Autowired
    public SupervisorTripsApplicationService(SupervisorRepository supervisorRepository, TripRepository tripRepository) {
        this.supervisorRepository = supervisorRepository;
        this.tripRepository = tripRepository;
    }

    public List<Trip> findAllTripsBySupervisorId(Long supervisorId) {
        List<Long> driversId = supervisorRepository.findAllDriversIdById(supervisorId);
        return tripRepository.findAllByDriver_IdIn(driversId);
    }
}
