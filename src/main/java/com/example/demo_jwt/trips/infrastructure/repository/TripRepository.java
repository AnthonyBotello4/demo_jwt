package com.example.demo_jwt.trips.infrastructure.repository;

import com.example.demo_jwt.trips.domain.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByDriver_Id(Long id);
    List<Trip> findAllByDriver_IdAndStatus_Id(Long driverId, Long statusId);

}