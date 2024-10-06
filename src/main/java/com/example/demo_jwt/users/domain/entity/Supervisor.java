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
@Table(name = "supervisor")
@AllArgsConstructor
@NoArgsConstructor
public class Supervisor extends User{

    @Column(name = "department", nullable = false, length = 50)
    private String department;
}
