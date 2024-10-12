package com.example.demo_jwt.users.interfaces.controller;

import com.example.demo_jwt.users.application.dto.request.SupervisorRegisterDto;
import com.example.demo_jwt.users.application.dto.response.SupervisorResponseDto;
import com.example.demo_jwt.users.domain.service.SupervisorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/supervisors")
@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
public class SupervisorController {
    
    private final SupervisorService supervisorService;
    
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SupervisorResponseDto>> findAll() {

        try{
            List<SupervisorResponseDto> supervisors = supervisorService.findAll();
            if (supervisors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(supervisors);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupervisorResponseDto> findById(@PathVariable Long id) {
        try {
            Optional<SupervisorResponseDto> supervisor = supervisorService.findById(id);

            return supervisor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupervisorResponseDto> save(@Valid @RequestBody SupervisorRegisterDto supervisorRegisterDto) {

        try{
            SupervisorResponseDto supervisor = supervisorService.save(supervisorRegisterDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(supervisor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supervisorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supervisor> update(@PathVariable Long id, @RequestBody Supervisor supervisor) {
        supervisor.setId(id);
        return ResponseEntity.ok(supervisorService.save(supervisor));
    }*/
    
}
