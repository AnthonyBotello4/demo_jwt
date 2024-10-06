package com.example.demo_jwt.users.domain.repository;

import com.example.demo_jwt.users.domain.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    Optional<List<Supervisor>> findAllByName(String name);
    Optional<Supervisor> findByEmail(String email);
    Optional<Supervisor> findByUsername(String username);
}
