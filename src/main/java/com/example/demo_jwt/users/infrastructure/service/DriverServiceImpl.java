package com.example.demo_jwt.users.infrastructure.service;

import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.repository.DriverRepository;
import com.example.demo_jwt.users.domain.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Transactional
    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Optional<List<Driver>> findAll() {
        return Optional.of(driverRepository.findAll());
    }
}
