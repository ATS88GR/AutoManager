package com.education.projects.cars.manager.carsmanager.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchCriteria {
    @Positive(message = "Year should be positive")
    private Integer year;
    @Size(min = 1, max = 32, message = "32 characters max")
    private String model;
    @Size(min = 1, max = 32, message = "32 characters max")
    private String brand;
    @Positive (message = "Cost should be positive")
    @Digits(integer = 10, fraction = 0, message = " integer number of not more than 10 characters")
    private Integer cost;
}
