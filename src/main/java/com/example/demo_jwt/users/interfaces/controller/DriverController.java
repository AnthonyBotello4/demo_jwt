package com.example.demo_jwt.users.interfaces.controller;

import com.example.demo_jwt.security.domain.model.UserDetailsImpl;
import com.example.demo_jwt.users.application.dto.response.DriverPrivateProfileDto;
import com.example.demo_jwt.users.application.dto.response.DriverPublicProfileDto;
import com.example.demo_jwt.users.application.dto.request.DriverRegisterDto;
import com.example.demo_jwt.users.application.dto.response.DriverResponseDto;
import com.example.demo_jwt.users.domain.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverResponseDto>> findAll() {
        try{
            List<DriverResponseDto> drivers = driverService.findAll();

            if (drivers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(drivers);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        try {

            if (userDetails.getUserId().equals(id)) {
                Optional<DriverPrivateProfileDto> privateProfile = driverService.getPrivateProfile(id);
                return privateProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

            } else {
                Optional<DriverPublicProfileDto> publicProfile = driverService.getPublicProfile(id);
                return publicProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody DriverRegisterDto driver) {

        try {
            DriverResponseDto driverResponseDto = driverService.save(driver);
            return ResponseEntity.status(HttpStatus.CREATED).body(driverResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DRIVER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            if (driverService.existsById(id)) {
                driverService.delete(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverResponseDto> update(@PathVariable Long id, @Valid @RequestBody Driver driver) {
        driver.setId(id);
        return ResponseEntity.ok(driverService.save(driver));
    }*/

}
