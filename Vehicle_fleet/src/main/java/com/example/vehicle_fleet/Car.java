package com.example.vehicle_fleet;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Table(name = "cars")
@Setter
@Entity
public class Car {
    private Long id;

    @Getter
    private String model;

    @Getter
    private Integer manufactureYear;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Getter
    private LocalDate registrationDate;

    @Getter
    private String owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
