package com.example.demo_jwt.security.service;

import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.repository.DriverRepository;
import com.example.demo_jwt.users.domain.repository.SupervisorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final DriverRepository driverRepository;
    private final SupervisorRepository supervisorRepository;

    public UserDetailServiceImpl(DriverRepository driverRepository, SupervisorRepository supervisorRepository) {
        this.driverRepository = driverRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> driver = driverRepository.findByUsername(username);
        if (driver.isPresent()) {
            return User.builder()
                    .username(driver.get().getUsername())
                    .password(driver.get().getPassword()) // Asegúrate de que esté codificada con BCrypt
                    .authorities("ROLE_DRIVER") // Puedes ajustar esto según tus necesidades
                    .build();
        }

        System.out.println("username: " + username + "Not found in driver table");

        Optional<Supervisor> supervisor = supervisorRepository.findByUsername(username);
        if (supervisor.isPresent()) {
            return User.builder()
                    .username(supervisor.get().getUsername())
                    .password(supervisor.get().getPassword()) // Asegúrate de que esté codificada con BCrypt
                    .authorities("ROLE_SUPERVISOR") // Puedes ajustar esto según tus necesidades
                    .build();
        }

        System.out.println("username: " + username + "Not found in supervisor table");

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}