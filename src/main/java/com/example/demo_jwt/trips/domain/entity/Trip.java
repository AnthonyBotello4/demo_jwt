package com.example.demo_jwt.trips.domain.entity;

import com.example.demo_jwt.trips.domain.enums.Status;
import com.example.demo_jwt.users.domain.entity.Driver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "trip")
@AllArgsConstructor
@NoArgsConstructor
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "driver_id")
    //private Driver driver;

    @Column(name = "driver_id")
    private Long driverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private TripStatus status;
}
