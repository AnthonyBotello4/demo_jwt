package com.example.demo_jwt.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class DriverDto{
    private Long id;
    private String name;
    private String firstLastName;
    private String secondLastName;

    public DriverDto() {
        this.id = null;
        this.name = "";
        this.firstLastName = "";
        this.secondLastName = "";
    }
}
