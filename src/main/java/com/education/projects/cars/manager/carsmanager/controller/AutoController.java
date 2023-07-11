package com.education.projects.cars.manager.carsmanager.controller;

import com.education.projects.cars.manager.carsmanager.model.Car;
import com.education.projects.cars.manager.carsmanager.service.DBCarServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@Tag(name = "Cars API")
public class AutoController {

    @Autowired
    private DBCarServiceImpl dbCarServiceImpl;

    @Operation(summary = "Creates new row in database with car information",
            description = "Returns created car information from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @PostMapping("/cars")  //url
    public Car createCar(@RequestBody Car car){
        log.info("Create car ={}", car);
        return dbCarServiceImpl.createAuto(car);
    }

    @Operation(summary = "Updates car information by id",
            description = "Returns updated car information from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id){
        log.info("Update car with id ={}, update car info {}", id, car);
        return dbCarServiceImpl.updateAuto(car, id);
    }

    @Operation(summary = "Gets information about all cars from database",
            description = "Returns collection of car objects from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @GetMapping("/cars")
    public Collection <Car> getCars() {
        log.info("Get all car info");
        return dbCarServiceImpl.getAllCars();
    }

    @Operation(summary = "Gets car by id",
            description = "Returns id car information from database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Long id) {
        log.info("Gets car with id ={}", id);
        return dbCarServiceImpl.getCarById(id);
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
