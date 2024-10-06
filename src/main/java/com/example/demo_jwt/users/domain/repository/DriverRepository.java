package com.example.demo_jwt.users.domain.repository;

import com.example.demo_jwt.users.domain.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

 @Repository
public interface DriverRepository  extends JpaRepository<Driver, Long> {

    Optional<List<Driver>> findAllByName(String name);
    Optional<Driver> findByEmail(String email);
    Optional<Driver> findByUsername(String username);
}
