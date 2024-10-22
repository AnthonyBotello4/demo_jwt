package com.example.demo_jwt.trips.infrastructure.repository;

import com.example.demo_jwt.trips.domain.entity.Trip;
import com.example.demo_jwt.trips.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByDriverId(Long id);
    List<Trip> findAllByDriverIdAndStatus_Id(Long driverId, Long statusId);
    List<Trip> findAllByDriverIdIn(List<Long> driverIds);
    List<Trip> findAllByStatus_Id (Long statusId);
}