package com.education.projects.cars.manager.carsmanager.controller;

import com.education.projects.cars.manager.carsmanager.model.Car;
import com.education.projects.cars.manager.carsmanager.service.DBCarServiceImpl;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
//@Tag(name = "Cars API")
public class AutoController {

    @Autowired
    private DBCarServiceImpl dbCarServiceImpl;

    @PostMapping("/cars")  //url
    /**
     * Creates new row in database with car information
     */
    public Car createCar(@RequestBody Car car){
        log.info("Create car ={}", car);
        return dbCarServiceImpl.createAuto(car);  //returns object from database
    }
    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id){
        log.info("Update car with id ={}, update car info {}", id, car);
        return dbCarServiceImpl.updateAuto(car, id);
    }

    @GetMapping("/cars")
    public Collection <Car> getCars() {
        return dbCarServiceImpl.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable Long id) {

        return dbCarServiceImpl.getCarById(id);
    }

    @DeleteMapping("/cars/{id}")
    /**
     * removes the row with id from database
     */
    public void deleteCarById(@PathVariable Long id) {
        dbCarServiceImpl.deleteCarById(id);
    }

}
