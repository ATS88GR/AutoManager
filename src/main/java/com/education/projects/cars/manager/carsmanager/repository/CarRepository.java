package com.education.projects.cars.manager.carsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.education.projects.cars.manager.carsmanager.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
