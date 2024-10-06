package com.example.demo_jwt.users.domain.service;

import com.example.demo_jwt.users.domain.entity.Supervisor;

import java.util.List;
import java.util.Optional;

public interface SupervisorService {
    Supervisor save(Supervisor supervisor);
    void delete(Long id);
    Optional<Supervisor> findById(Long id);
    Optional<List<Supervisor>> findAll();
}
