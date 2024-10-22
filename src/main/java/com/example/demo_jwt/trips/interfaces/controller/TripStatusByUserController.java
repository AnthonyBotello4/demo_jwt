package com.example.demo_jwt.trips.interfaces.controller;

import com.example.demo_jwt.trips.application.dto.response.TripInformationDto;
import com.example.demo_jwt.trips.domain.service.TripStatusByUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/trips/status")
public class TripStatusByUserController {

    private final TripStatusByUserService service;

    public TripStatusByUserController(TripStatusByUserService tripService) {
        this.service = tripService;
    }

    @GetMapping(value = "{statusId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllByStatus(@PathVariable Long statusId) {
        try {

            List<TripInformationDto> trips = service.findAllByStatus(statusId);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(value = "/all/supervisor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllBySupervisorId(@PathVariable Long id) {
        try {
            List<TripInformationDto> trips = service.findAllBySupervisorId(id);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/all/driver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllByDriverId(@PathVariable Long id) {
        try {
            List<TripInformationDto> trips = service.findAllByDriverId(id);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "{statusId}/driver/{driverId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllByDriverIdAndStatus(@PathVariable Long driverId, @PathVariable Long statusId) {
        try {
            List<TripInformationDto> trips = service.findAllByDriverIdAndStatus(driverId, statusId);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "{statusId}/supervisor/{supervisorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllBySupervisorIdAndStatus(@PathVariable Long supervisorId, @PathVariable Long statusId) {
        try {
            List<TripInformationDto> trips = service.findAllBySupervisorIdAndStatus(supervisorId, statusId);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/driver/{driverId}/supervisor/{supervisorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllByDriverIdAndSupervisorId(@PathVariable Long driverId, @PathVariable Long supervisorId) {
        try {
            List<TripInformationDto> trips = service.findAllByDriverIdAndSupervisorId(driverId, supervisorId);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    @GetMapping(value = "{status}/driver/{id}/supervisor/{supervisorId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripInformationDto>> findAllByDriverIdAndSupervisorIdAndStatus(@PathVariable Long id, @PathVariable Long supervisorId, @PathVariable String status) {
        try {
            List<TripInformationDto> trips = tripService.findAllByDriverIdAndSupervisorIdAndStatus(id, supervisorId, status);

            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(trips);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/
}
