package com.example.demo_jwt.users.domain.service;

import com.example.demo_jwt.users.domain.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver save(Driver driver);
    void delete(Long id);
    Optional<Driver> findById(Long id);
    Optional<List<Driver>> findAll();
}
