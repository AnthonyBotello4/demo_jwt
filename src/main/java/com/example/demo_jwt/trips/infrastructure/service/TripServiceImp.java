package com.example.demo_jwt.trips.infrastructure.service;

import com.example.demo_jwt.trips.application.dto.request.TripRegisterDto;
import com.example.demo_jwt.trips.application.dto.response.TripCreatedDto;
import com.example.demo_jwt.trips.application.dto.response.TripInformationDto;
import com.example.demo_jwt.trips.application.mapper.TripMapper;
import com.example.demo_jwt.trips.domain.entity.Trip;
import com.example.demo_jwt.trips.domain.entity.TripStatus;
import com.example.demo_jwt.trips.domain.enums.Status;
import com.example.demo_jwt.trips.domain.model.DriverDto;
import com.example.demo_jwt.trips.domain.service.TripService;
import com.example.demo_jwt.trips.infrastructure.acl.DriverAcl;
import com.example.demo_jwt.trips.infrastructure.repository.TripRepository;
import com.example.demo_jwt.trips.infrastructure.repository.TripStatusRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TripServiceImp  implements TripService {

    private final TripRepository tripRepository;
    private final TripStatusRepository tripStatusRepository;

    private final Logger logger = LoggerFactory.getLogger(TripServiceImp.class);

    private final DriverAcl driverAcl;

    private final TripMapper tripMapper;

    @Autowired
    public TripServiceImp(TripRepository tripRepository, TripStatusRepository tripStatusRepository, DriverAcl driverAcl, TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.tripStatusRepository = tripStatusRepository;
        this.driverAcl = driverAcl;
        this.tripMapper = tripMapper;
    }

    @Override
    public Optional<TripInformationDto> findById(Long id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip != null) {
            DriverDto driverDto = driverAcl.findDriverById(trip.getDriverId()).orElse(null);
            if (driverDto == null) {
                driverDto = new DriverDto();
            }

            return Optional.of(tripMapper.tripToTripInformationDto(trip, driverDto));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<TripInformationDto> findAll() {
        //Obtener todos los ids de los conductores
        List<Trip> trips = tripRepository.findAll();

        List<Long> driverIds = trips
                .stream()
                .map(Trip::getDriverId)
                .distinct()
                .collect(Collectors.toList());

        //Obtener los conductores
        List<DriverDto> drivers = driverAcl.findAllDriversByIdIn(driverIds);

        return tripsAssembler(trips, drivers);
    }

    @Override
    public boolean existsById(Long id) {
        return tripRepository.existsById(id);
    }

    //@Transactional
    //@Override
    public TripCreatedDto save2(TripRegisterDto tripRegisterDto) {

        // Mapear el objeto TripRegisterDto a un objeto Trip
        Trip trip = tripMapper.registerDtoToTrip(tripRegisterDto);

        // Asignar el status del viaje
        TripStatus tripStatus = tripStatusRepository.findByStatus(Status.PENDING)
                .orElseThrow(() -> new RuntimeException("Error: El status del viaje no existe en la base de datos"));

        trip.setStatus(tripStatus);

        //Verificar si el conductor existe
        Optional<DriverDto> driver = driverAcl.findDriverById(trip.getDriverId());

        if (driver.isPresent()) {
            // Persistir el viaje
            trip = tripRepository.save(trip);

            logger.info("Driver found: {}", driver.get().toString());

            // Mapear el objeto Trip a un objeto TripCreatedDto
            return tripMapper.tripToTripCreatedDto(trip, driver.get());

        } else {
            throw new RuntimeException("Error: El conductor con el id '" + trip.getDriverId() + "' no existe en la base de datos");

        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public TripCreatedDto save(TripRegisterDto tripRegisterDto) {

        logger.info("Inicio del método save en TripService");

        // Mapear el objeto TripRegisterDto a un objeto Trip
        Trip trip = tripMapper.registerDtoToTrip(tripRegisterDto);

        logger.info("Trip mapeado: {}", trip);

        // Asignar el status del viaje
        TripStatus tripStatus = tripStatusRepository.findByStatus(Status.PENDING)
                .orElseThrow(() -> new RuntimeException("Error: El status del viaje no existe en la base de datos"));

        logger.info("Status del viaje encontrado: {}", tripStatus);

        trip.setStatus(tripStatus);

        // Verificar si el conductor existe
        Optional<DriverDto> driver = driverAcl.findDriverById(trip.getDriverId());

        logger.info("Respuesta del ACL para conductor: {}", driver);

        if (driver.isPresent()) {
            // Persistir el viaje
            trip = tripRepository.save(trip);

            logger.info("Trip guardado exitosamente: {}", trip);

            // Mapear el objeto Trip a un objeto TripCreatedDto
            return tripMapper.tripToTripCreatedDto(trip, driver.get());

        } else {
            logger.error("Error: El conductor con el id '{}' no existe en la base de datos", trip.getDriverId());
            throw new RuntimeException("Error: El conductor con el id '" + trip.getDriverId() + "' no existe en la base de datos");
        }
    }

    @Override
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    /*public List<TripInformationDto> findAllBySupervisorIdAndStatus(Long id, Status status) {
        return tripRepository.findAllBySupervisorIdAndStatus(id, status).stream().map(tripMapper::tripToTripInformationDto).toList();
    }*/

    private List<TripInformationDto> tripsAssembler(List<Trip> trips, List<DriverDto> drivers)  {
        return trips.stream()
                .map(trip -> {
                    DriverDto driverDto = drivers.stream()
                            .filter(driver -> driver.getId().equals(trip.getDriverId()))
                            .findFirst()
                            .orElse(new DriverDto());

                    return tripMapper.tripToTripInformationDto(trip, driverDto);

                }).collect(Collectors.toList());
    }

}
