package com.example.demo_jwt.security.infrastructure;

import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.repository.DriverRepository;
import com.example.demo_jwt.users.domain.repository.SupervisorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DriverRepository driverRepository;
    private final SupervisorRepository supervisorRepository;

    public UserDetailsServiceImpl(DriverRepository driverRepository, SupervisorRepository supervisorRepository) {
        this.driverRepository = driverRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> driver = driverRepository.findByUser_Username(username);
        if (driver.isPresent()) {
            return User.builder()
                    .username(driver.get().getUser().getUsername())
                    .password(driver.get().getUser().getPassword())
                    .authorities("ROLE_DRIVER") //Todo: Adjust this according to the roles saved in the database
                    .build();
        }

        System.out.println("username: " + username + "Not found in driver table");

        Optional<Supervisor> supervisor = supervisorRepository.findByUser_Username(username);
        if (supervisor.isPresent()) {
            return User.builder()
                    .username(supervisor.get().getUser().getUsername())
                    .password(supervisor.get().getUser().getPassword())
                    .authorities("ROLE_SUPERVISOR")  // Todo: Adjust this according to the roles saved in the database
                    .build();
        }

        System.out.println("username: " + username + "Not found in supervisor table");

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}