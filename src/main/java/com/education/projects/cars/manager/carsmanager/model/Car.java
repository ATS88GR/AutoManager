package com.education.projects.cars.manager.carsmanager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {
    @Schema (name = "id", description = "Car id", example = "1")
    private int id;
    @Schema (name = "year", description = "Year of production", example = "2023")
    private int year;
    @Schema (name = "brand", description = "Car manufacturer brand", example = "VW")
    private String brand;
    @Schema (name = "model", description = "Car model", example = "Polo")
    private String model;
    @Schema (name = "cost", description = "Car cost", example = "5000")
    private int cost;

    public Car() {
    }
}
