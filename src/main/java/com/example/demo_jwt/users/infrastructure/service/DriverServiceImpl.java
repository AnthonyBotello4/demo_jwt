package com.example.demo_jwt.users.infrastructure.service;

import com.example.demo_jwt.users.application.dto.response.DriverPrivateProfileDto;
import com.example.demo_jwt.users.application.dto.response.DriverPublicProfileDto;
import com.example.demo_jwt.users.application.dto.request.DriverRegisterDto;
import com.example.demo_jwt.users.application.dto.response.DriverResponseDto;
import com.example.demo_jwt.users.application.mapper.DriverMapper;
import com.example.demo_jwt.users.domain.entity.Driver;
import com.example.demo_jwt.users.domain.entity.Role;
import com.example.demo_jwt.users.domain.entity.User;
import com.example.demo_jwt.users.domain.enums.RoleName;
import com.example.demo_jwt.users.domain.repository.DriverRepository;
import com.example.demo_jwt.users.domain.repository.RoleRepository;
import com.example.demo_jwt.users.domain.repository.UserRepository;
import com.example.demo_jwt.users.domain.service.DriverService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class DriverServiceImpl implements DriverService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final RoleRepository roleRepository;
    private final DriverMapper driverMapper;
    private final PasswordEncoder passwordEncoder;

    public DriverServiceImpl(UserRepository userRepository, DriverRepository driverRepository, RoleRepository roleRepository, DriverMapper driverMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.roleRepository = roleRepository;
        this.driverMapper = driverMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public DriverResponseDto save(DriverRegisterDto driverRegisterDto) {

        // Mapear el objeto DriverRegisterDto a un objeto Driver
        Driver driver = driverMapper.registerDtoToDriver(driverRegisterDto);

        // Persistir el objeto User primero
        User user = driver.getUser();

        // Recuperar el rol desde la base de datos
        Role driverRole = roleRepository.findByRoleName(RoleName.ROLE_DRIVER)
                .orElseThrow(() -> new RuntimeException("Error: El rol ROLE_DRIVER no existe en la base de datos"));

        // Asignar el rol al usuario
        user.setRoles(Set.of(driverRole));

        // Cifrar la contraseña del usuario antes de guardarlo
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(driver.getUser());

        // Asignar el objeto User persistido al objeto Driver
        driver.setUser(savedUser);
        Driver savedDriver = driverRepository.save(driver);

        return driverMapper.driverToResponseDto(savedDriver);
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Optional<DriverResponseDto> findById(Long id) {

        return driverRepository.findById(id)
                .map(driverMapper::driverToResponseDto);
    }

    @Override
    public List<DriverResponseDto> findAll() {

        return driverRepository.findAll()
                .stream()
                .map(driverMapper::driverToResponseDto)
                .toList();
    }

    @Override
    public List<DriverResponseDto> findAllByIdIn(List<Long> ids) {
        return driverRepository.findAllByIdIn(ids)
                .stream()
                .map(driverMapper::driverToResponseDto)
                .toList();
    }

    @Override
    public Optional<DriverPrivateProfileDto> getPrivateProfile(Long id) {
        return driverRepository.findById(id)
                .map(driverMapper::driverToPrivateProfileDto);
    }

    @Override
    public Optional<DriverPublicProfileDto> getPublicProfile(Long id) {
        return driverRepository.findById(id)
                .map(driverMapper::driverToPublicProfileDto);
    }

    @Override
    public boolean existsById(Long id) {
        return driverRepository.existsById(id);
    }
}
