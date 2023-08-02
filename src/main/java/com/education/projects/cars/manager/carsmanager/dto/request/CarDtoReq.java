package com.education.projects.cars.manager.carsmanager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarDtoReq {

    @Schema(name = "year", description = "Year of production", example = "2023")
    @NotNull (message = "Year should not be empty")
    @Positive (message = "Year should be positive")
    @Digits(integer = 4, fraction = 0, message = "integer number of not more than 4 characters")
    private Integer year;

    @Schema (name = "brand", description = "Car manufacturer brand", example = "VW")
    @NotBlank (message = "Brand should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String brand;

    @Schema (name = "model", description = "Car model", example = "Polo")
    @NotBlank (message = "Brand should not be blank")
    @Size(min = 1, max = 32, message = "32 characters max")
    private String model;

    @Schema (name = "cost", description = "Car cost", example = "5000")
    @NotNull (message = "Cost should not be empty")
    @Positive (message = "Cost should be positive")
    @Digits(integer = 10, fraction = 0, message = " integer number of not more than 10 characters")
    private Integer cost;
}
