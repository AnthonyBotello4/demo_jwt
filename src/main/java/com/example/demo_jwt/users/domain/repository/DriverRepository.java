package com.example.demo_jwt.users.domain.repository;

import com.example.demo_jwt.users.domain.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

 @Repository
public interface DriverRepository  extends JpaRepository<Driver, Long> {

    Optional<List<Driver>> findAllByUser_Name(String name);
    Optional<Driver> findByUser_Email (String email);
    Optional<Driver> findByUser_Username (String username);
    List<Driver> findAllByIdIn(List<Long> ids);
}
