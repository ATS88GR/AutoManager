package com.education.projects.cars.manager.carsmanager.utils;

import com.education.projects.cars.manager.carsmanager.entity.Car;
import java.util.ArrayList;

/**
 * The class to create test list of a car objects
 */
public class TestCar {

    /**
     * Creates Car objects list
     * @return Car objects list
     */
    public static ArrayList<Car> getGarage() {
        ArrayList<Car> garage = new ArrayList<>();
        garage.add(new Car(1,2002,"Mercedes", "w140", 15000));
        garage.add(new Car(2,1996,"Mercedes", "w140", 10000));
        garage.add(new Car(3,2019,"VW", "Polo", 10000));
        garage.add(new Car(4,2016,"VW", "Golf", 12000));
        garage.add(new Car(5,2012,"VW", "Passat", 8000));
        garage.add(new Car(6,1998,"Audi", "100", 500));
        garage.add(new Car(7,2010,"Audi", "A4", 8000));
        garage.add(new Car(8,2012,"Audi", "A4", 9000));
        garage.add(new Car(9,2020,"Audi", "A5", 30000));
        garage.add(new Car(10,2005,"Volvo", "S90", 8000));
        garage.add(new Car(11,2014,"Volvo", "S80", 18000));
        garage.add(new Car(12,2020,"Volvo", "S60", 20000));
        garage.add(new Car(13,2022,"Volvo", "S60", 23000));
        garage.add(new Car(14,2006,"Suzuki", "Baleno", 3000));
        garage.add(new Car(15,2019,"Suzuki", "Swift", 14000));
        return garage;
    }
}
