package com.example.demo_jwt.users.domain.repository;

import com.example.demo_jwt.users.domain.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    Optional<List<Supervisor>> findAllByUser_Name(String name);
    Optional<Supervisor> findByUser_Email(String email);
    Optional<Supervisor> findByUser_Username(String username);
}
