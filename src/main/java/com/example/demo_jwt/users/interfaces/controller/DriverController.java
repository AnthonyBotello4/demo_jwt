package com.example.demo_jwt.users.interfaces.controller;

import com.example.demo_jwt.users.application.dto.request.DriverRegisterDto;
import com.example.demo_jwt.users.application.dto.response.DriverResponseDto;
import com.example.demo_jwt.users.domain.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverResponseDto> findById(@PathVariable Long id) {

        try {
            Optional<DriverResponseDto> driver = driverService.findById(id);

            return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        driverService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverResponseDto> update(@PathVariable Long id, @Valid @RequestBody Driver driver) {
        driver.setId(id);
        return ResponseEntity.ok(driverService.save(driver));
    }*/

}
