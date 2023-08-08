package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;

import java.sql.SQLException;
import java.util.Collection;

/**
 * The interface for service Car objects information
 */
public interface CarService {

    CarDtoResp createAuto(CarDtoReq carDtoReq) throws Exception;

    CarDtoResp updateAuto(CarDtoReq carDtoReq, Integer id) throws Exception;

    Collection<CarDtoResp> getAllCars() throws Exception;

    CarDtoResp getCarById(Integer id) throws Exception;

    void deleteCarById(Integer id) throws Exception;

    Collection<Car> getSortedFilteredCars(String sortBy, String sortDirection, String filter)
            throws Exception;
}
