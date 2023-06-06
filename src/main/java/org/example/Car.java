package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Car implements Serializable {

    private int year;
    private String brand;
    private String model;
    private int cost;

}
