package com.example.demo_jwt.users.interfaces.controller;

import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.service.DriverService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;

    public DriverController(DriverService driverService, PasswordEncoder passwordEncoder) {
        this.driverService = driverService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Driver>> findAll() {
        return driverService.findAll()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> findById(@PathVariable Long id) {
        return driverService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> save(@RequestBody Driver driver) {
        String password = passwordEncoder.encode(driver.getPassword());
        driver.setPassword(password);
        return ResponseEntity.ok(driverService.save(driver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        driverService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> update(@PathVariable Long id, @RequestBody Driver driver) {
        driver.setId(id);
        return ResponseEntity.ok(driverService.save(driver));
    }

}
