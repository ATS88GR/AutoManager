package com.education.projects.cars.manager.carsmanager.mapper;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapperResp {
    CarMapperResp INSTANCE = Mappers.getMapper( CarMapperResp.class );

    CarDtoResp carToCarDto(Car car);
    Car carDtoToCar(CarDtoResp carDtoResp);
}
