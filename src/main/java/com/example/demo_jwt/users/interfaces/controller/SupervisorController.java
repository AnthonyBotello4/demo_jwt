package com.example.demo_jwt.users.interfaces.controller;

import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.service.SupervisorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/supervisors")
public class SupervisorController {
    
    private final SupervisorService supervisorService;
    
    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Supervisor>> findAll() {
        return supervisorService.findAll()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supervisor> findById(@PathVariable Long id) {
        return supervisorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supervisor> save(@RequestBody Supervisor supervisor) {
        return ResponseEntity.ok(supervisorService.save(supervisor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supervisorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supervisor> update(@PathVariable Long id, @RequestBody Supervisor supervisor) {
        supervisor.setId(id);
        return ResponseEntity.ok(supervisorService.save(supervisor));
    }
    
}
