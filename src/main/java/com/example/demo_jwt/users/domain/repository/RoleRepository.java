package com.example.demo_jwt.users.domain.repository;

import com.example.demo_jwt.users.domain.entity.Role;
import com.example.demo_jwt.users.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
