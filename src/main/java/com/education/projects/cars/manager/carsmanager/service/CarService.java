package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * The interface for service Car objects information
 */
public interface CarService {

    CarDtoResp createAuto(CarDtoReq carDtoReq) throws SQLException;

    CarDtoResp updateAuto(CarDtoReq carDtoReq, Integer id) throws SQLException;

    List<CarDtoResp> getAllCars();

    CarDtoResp getCarById(Integer id) throws SQLException;

    void deleteCarById(Integer id) throws SQLException;

    Collection<Car> getSortedFilteredCars(String sortBy, String sortDirection, String filter)
            throws SQLException;
}
