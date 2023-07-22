package com.education.projects.cars.manager.carsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.education.projects.cars.manager.carsmanager.entity.Car;

import java.util.Collection;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
   /* @Query("select")
    Collection <Car> findAllByBrand();*/
}
