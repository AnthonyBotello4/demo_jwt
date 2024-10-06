package com.example.demo_jwt.users.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "driver")
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends User{

    @Column(name = "plate", nullable = false)
    private String plate;
}
