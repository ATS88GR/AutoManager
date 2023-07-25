package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.entity.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The interface for service Car objects information
 */
public interface CarService {

    Car createAuto(Car car) throws SQLException;

    Car updateAuto(Car car, Integer id) throws SQLException;

    Collection<Car> getAllCars();

    Car getCarById(Integer id) throws SQLException;

    void deleteCarById(Integer id) throws SQLException;

    Collection<Car> getSortedFilteredCars(String sortBy, String sortDirection, String filter)
            throws SQLException;
}
