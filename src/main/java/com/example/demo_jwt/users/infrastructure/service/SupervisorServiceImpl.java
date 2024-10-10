package com.example.demo_jwt.users.infrastructure.service;

import com.example.demo_jwt.users.application.dto.request.SupervisorRegisterDto;
import com.example.demo_jwt.users.application.dto.response.SupervisorResponseDto;
import com.example.demo_jwt.users.application.mapper.SupervisorMapper;
import com.example.demo_jwt.users.domain.entity.Role;
import com.example.demo_jwt.users.domain.entity.Supervisor;
import com.example.demo_jwt.users.domain.entity.User;
import com.example.demo_jwt.users.domain.enums.RoleName;
import com.example.demo_jwt.users.domain.repository.RoleRepository;
import com.example.demo_jwt.users.domain.repository.SupervisorRepository;
import com.example.demo_jwt.users.domain.repository.UserRepository;
import com.example.demo_jwt.users.domain.service.SupervisorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class SupervisorServiceImpl implements SupervisorService {

    private final UserRepository userRepository;
    private final SupervisorRepository supervisorRepository;
    private final RoleRepository roleRepository;
    private final SupervisorMapper supervisorMapper;
    private final PasswordEncoder passwordEncoder;

    public SupervisorServiceImpl(UserRepository userRepository, SupervisorRepository supervisorRepository, RoleRepository roleRepository, SupervisorMapper supervisorMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.supervisorRepository = supervisorRepository;
        this.roleRepository = roleRepository;
        this.supervisorMapper = supervisorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public SupervisorResponseDto save(SupervisorRegisterDto supervisorRegisterDto) {

        // Mapear el objeto SupervisorRegisterDto a un objeto Supervisor
        Supervisor supervisor = supervisorMapper.registerDtoToSupervisor(supervisorRegisterDto);

        // Persistir el objeto User primero
        User user = supervisor.getUser();

        // Recuperar el rol desde la base de datos
        Role supervisorRole = roleRepository.findByRoleName(RoleName.ROLE_SUPERVISOR)
                .orElseThrow(() -> new RuntimeException("Error: El rol ROLE_SUPERVISOR no existe en la base de datos"));

        // Asignar el rol al usuario
        user.setRoles(Set.of(supervisorRole));

        // Cifrar la contraseña del usuario antes de guardarlo
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(supervisor.getUser());

        // Asignar el objeto User persistido al objeto Supervisor
        supervisor.setUser(savedUser);
        Supervisor savedSupervisor = supervisorRepository.save(supervisor);

        return supervisorMapper.supervisorToResponseDto(savedSupervisor);
    }

    @Override
    public void delete(Long id) {
        supervisorRepository.deleteById(id);
    }

    @Override
    public Optional<SupervisorResponseDto> findById(Long id) {

        return supervisorRepository.findById(id)
                .map(supervisorMapper::supervisorToResponseDto);
    }

    @Override
    public List<SupervisorResponseDto> findAll() {

        return supervisorRepository.findAll()
                .stream()
                .map(supervisorMapper::supervisorToResponseDto)
                .toList();
    }
}
