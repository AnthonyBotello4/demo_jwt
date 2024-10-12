package com.example.demo_jwt.security.infrastructure;

import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.repository.DriverRepository;
import com.example.demo_jwt.users.domain.repository.RoleRepository;
import com.example.demo_jwt.users.domain.repository.SupervisorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DriverRepository driverRepository;
    private final SupervisorRepository supervisorRepository;

    public UserDetailsServiceImpl(DriverRepository driverRepository, SupervisorRepository supervisorRepository, RoleRepository roleRepository) {
        this.driverRepository = driverRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> driver = driverRepository.findByUser_Username(username);
        if (driver.isPresent()) {

            System.out.println("username: " + username + " encontrado en la tabla driver");
            System.out.println("Roles: " + driver.get().getUser().getRoles().stream()
                    .map(role -> role.getRoleName().name())
                    .collect(Collectors.joining(", ")));

            // Mapear roles a objetos GrantedAuthority
            List<GrantedAuthority> authorities = driver.get().getUser().getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                    .collect(Collectors.toList());

            return User.builder()
                    .username(driver.get().getUser().getUsername())
                    .password(driver.get().getUser().getPassword())
                    .authorities(authorities)
                    .build();
        }

        System.out.println("username: " + username + "Not found in driver table");

        Optional<Supervisor> supervisor = supervisorRepository.findByUser_Username(username);
        if (supervisor.isPresent()) {

            System.out.println("username: " + username + " encontrado en la tabla supervisor");
            System.out.println("Roles: " + supervisor.get().getUser().getRoles().stream()
                    .map(role -> role.getRoleName().name())
                    .collect(Collectors.joining(", ")));

            // Mapear roles a objetos GrantedAuthority
            List<GrantedAuthority> authorities = supervisor.get().getUser().getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                    .collect(Collectors.toList());

            return User.builder()
                    .username(supervisor.get().getUser().getUsername())
                    .password(supervisor.get().getUser().getPassword())
                    .authorities(authorities)
                    .build();
        }

        System.out.println("username: " + username + "Not found in supervisor table");

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}