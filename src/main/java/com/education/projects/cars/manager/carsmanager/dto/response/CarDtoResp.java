package com.education.projects.cars.manager.carsmanager.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarDtoResp {

    @Schema(name = "id", description = "Car id", example = "1")
    private Integer Id;

    @Schema (name = "year", description = "Year of production", example = "2023")
    private Integer year;

    @Schema (name = "brand", description = "Car manufacturer brand", example = "VW")
    private String brand;

    @Schema (name = "model", description = "Car model", example = "Polo")
    private String model;

    @Schema (name = "cost", description = "Car cost", example = "5000")
    private Integer cost;
}
