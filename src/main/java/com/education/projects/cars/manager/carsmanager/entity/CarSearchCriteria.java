package com.education.projects.cars.manager.carsmanager.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchCriteria {
    private Integer year;
    private String model;
    private String brand;
    private Integer cost;
}
