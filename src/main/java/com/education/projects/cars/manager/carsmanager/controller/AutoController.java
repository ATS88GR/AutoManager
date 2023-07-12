package com.education.projects.cars.manager.carsmanager.controller;

import com.education.projects.cars.manager.carsmanager.model.Car;
import com.education.projects.cars.manager.carsmanager.service.DBCarServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;

@RestController
@Slf4j
@Tag(name = "Cars API")
public class AutoController {

    @Autowired
    private DBCarServiceImpl dbCarServiceImpl;

    @Operation(summary = "Creates new row in database with car information",
            description = "Returns created car information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @PostMapping("/cars")  //url
    public Car createCar(@RequestBody Car car){
        log.info("Create car ={}", car);
        try {
            return dbCarServiceImpl.createAuto(car);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return null;
    }

    @Operation(summary = "Updates car information by id",
            description = "Returns updated car information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The car was not found")
    })
    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id){
        try {
            log.info("Update car with id ={}, update car info {}", id, car);
            return dbCarServiceImpl.updateAuto(car, id);
        } catch (Exception ex){
            log.error("Error: {}", ex.getMessage());
        }
        return null;
    }

    @Operation(summary = "Gets information about all cars from database",
            description = "Returns collection of car objects from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @GetMapping("/cars")
    public Collection <Car> getCars() {
        log.info("Get all car info");
        return dbCarServiceImpl.getAllCars();
    }

    @Operation(summary = "Gets sorted and filtered information about cars from database",
            description = "Returns collection of car objects from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @GetMapping("/sortedCars")
    public Collection <Car> getSortFilterCars(
            @RequestParam ("sortBy") String sortBy,
            @RequestParam ("sortDirection") String sortDirection,
            @RequestParam ("filter") String filter
    ) {
        log.info("Get sorted and filtered car info");
        try {
            return dbCarServiceImpl.getSortedFilteredCars(sortBy, sortDirection, filter);
        } catch (SQLException e) {
            log.error("Error: {}", e.getMessage());
        }
        return null;
    }

    @Operation(summary = "Gets car by id",
            description = "Returns id car information from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Long id) {
        log.info("Gets car with id ={}", id);
        try {
            return dbCarServiceImpl.getCarById(id);
        } catch (SQLException e) {
            log.error("Error: {}", e.getMessage());
        }
        return null;
    }

    @Operation(summary = "Deletes car by id",
            description = "Removes the row with id from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @DeleteMapping("/cars/{id}")
    public void deleteCarById(@PathVariable Long id) {
        log.info("Deletes car with id ={}", id);
        dbCarServiceImpl.deleteCarById(id);
    }

}
