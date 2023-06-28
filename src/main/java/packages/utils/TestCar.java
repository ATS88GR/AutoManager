package packages.utils;

import packages.model.Car;
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
        garage.add(new Car(2002,"Mercedes", "w140", 15000));
        garage.add(new Car(1996,"Mercedes", "w140", 10000));
        garage.add(new Car(2019,"VW", "Polo", 10000));
        garage.add(new Car(2016,"VW", "Golf", 12000));
        garage.add(new Car(2012,"VW", "Passat", 8000));
        garage.add(new Car(1998,"Audi", "100", 500));
        garage.add(new Car(2010,"Audi", "A4", 8000));
        garage.add(new Car(2012,"Audi", "A4", 9000));
        garage.add(new Car(2020,"Audi", "A5", 30000));
        garage.add(new Car(2005,"Volvo", "S90", 8000));
        garage.add(new Car(2014,"Volvo", "S80", 18000));
        garage.add(new Car(2020,"Volvo", "S60", 20000));
        garage.add(new Car(2022,"Volvo", "S60", 23000));
        garage.add(new Car(2006,"Suzuki", "Baleno", 3000));
        garage.add(new Car(2019,"Suzuki", "Swift", 14000));
        return garage;
    }
}
