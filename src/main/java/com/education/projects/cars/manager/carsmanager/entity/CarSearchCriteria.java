package com.education.projects.cars.manager.carsmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchCriteria {

    @Positive(message = "Year should be positive")
    @Schema (name = "year", description = "Year of production", example = "2023")
    private Integer year;

    @Size(min = 1, max = 32, message = "32 characters max")
    @Schema (name = "brand", description = "Car manufacturer brand", example = "VW")
    private String brand;

    @Size(min = 1, max = 32, message = "32 characters max")
    @Schema (name = "model", description = "Car model", example = "Polo")
    private String model;

    @Positive (message = "Cost should be positive")
    @Digits(integer = 10, fraction = 0, message = " integer number of not more than 10 characters")
    @Schema (name = "cost", description = "Car cost", example = "5000")
    private Integer cost;
}
