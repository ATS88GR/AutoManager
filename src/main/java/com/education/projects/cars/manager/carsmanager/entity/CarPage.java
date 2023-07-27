package com.education.projects.cars.manager.carsmanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
@Getter
@Setter
public class CarPage {
    private int pageNumber = 0;
    private int pageSize = 5;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "brand";
}
