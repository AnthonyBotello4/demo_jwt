package com.example.demo_jwt.users.infrastructure.service;

import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.repository.SupervisorRepository;
import com.example.demo_jwt.users.domain.service.SupervisorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;

    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public Supervisor save(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public void delete(Long id) {
        supervisorRepository.deleteById(id);
    }

    @Override
    public Optional<Supervisor> findById(Long id) {
        return supervisorRepository.findById(id);
    }

    @Override
    public Optional<List<Supervisor>> findAll() {
        return Optional.of(supervisorRepository.findAll());
    }
}
