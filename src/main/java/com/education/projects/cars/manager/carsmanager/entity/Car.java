package com.education.projects.cars.manager.carsmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "GARAGE")
public class Car {

    public Car(Integer year, String brand, String model, Integer cost) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.cost = cost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Car id", example = "1")
    @Column(name = "id", insertable = false)
    private Integer id;

    @Schema (name = "year", description = "Year of production", example = "2023")
    @Column(name = "year", nullable = false)
    private Integer year;

    @Schema (name = "brand", description = "Car manufacturer brand", example = "VW")
    @Column(name = "brand", nullable = false)
    private String brand;

    @Schema (name = "model", description = "Car model", example = "VW")
    @Column(name = "model", nullable = false)
    private String model;

    @Schema (name = "cost", description = "Car cost", example = "5000")
    @Column(name = "cost", nullable = false)
    private Integer cost;
}
