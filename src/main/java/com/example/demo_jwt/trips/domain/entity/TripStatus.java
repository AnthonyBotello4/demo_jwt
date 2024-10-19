package com.example.demo_jwt.trips.domain.entity;

import com.example.demo_jwt.trips.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "trip_status")
@AllArgsConstructor
@NoArgsConstructor
public class TripStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", unique = true, nullable = false)
    private Status status;
}
