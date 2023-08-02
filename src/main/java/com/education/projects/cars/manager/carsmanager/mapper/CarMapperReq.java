package com.education.projects.cars.manager.carsmanager.mapper;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapperReq {
    CarMapperReq INSTANCE = Mappers.getMapper( CarMapperReq.class );

    CarDtoReq carToCarDto(Car car);
    Car carDtoToCar(CarDtoReq carDtoReq);
}
