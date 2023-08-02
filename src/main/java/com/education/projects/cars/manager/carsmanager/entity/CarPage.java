package com.education.projects.cars.manager.carsmanager.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
@Getter
@Setter
public class CarPage {
    @NotEmpty
    @Min(value = 0, message = "min value is 0")
    private int pageNumber = 0;
    @NotEmpty
    @Min(value = 1, message = "min value is 1")
    private int pageSize = 5;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    @NotBlank
    private String sortBy = "brand";
}
