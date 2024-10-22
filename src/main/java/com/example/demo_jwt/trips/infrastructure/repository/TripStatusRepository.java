package com.example.demo_jwt.trips.infrastructure.repository;

import com.example.demo_jwt.trips.domain.entity.TripStatus;
import com.example.demo_jwt.trips.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripStatusRepository extends JpaRepository<TripStatus, Long> {

    Optional<TripStatus> findByStatus(Status status);
}
