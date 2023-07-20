package com.education.projects.cars.manager.carsmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "garage")
public class Car {
    @Id
    @Column(name = "id", insertable = false)
    private Integer id;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "cost", nullable = false)
    private Integer cost;
}
