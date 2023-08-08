package com.education.projects.cars.manager.carsmanager.mapper;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDtoResp carToCarDto(Car car);
    Car carDtoToCar(CarDtoReq carDtoReq);
    List<CarDtoResp> carListToCarDtoList(List<Car> cars);
}
