package org.example;

import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.JsonService;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static String path = System.getProperty("user.dir");
    public static void main(String[] args) {
        ArrayList<Car> Garage = new ArrayList<>();
        Garage.add(new Car(2002,"Mercedes", "w140", 15000));
        Garage.add(new Car(1996,"Mercedes", "w140", 10000));
        Garage.add(new Car(2019,"VW", "Polo", 10000));
        Garage.add(new Car(2016,"VW", "Golf", 12000));
        Garage.add(new Car(2012,"VW", "Passat", 8000));
        Garage.add(new Car(1998,"Audi", "100", 500));
        Garage.add(new Car(2010,"Audi", "A4", 8000));
        Garage.add(new Car(2012,"Audi", "A4", 9000));
        Garage.add(new Car(2020,"Audi", "A5", 30000));
        Garage.add(new Car(2005,"Volvo", "S90", 8000));
        Garage.add(new Car(2014,"Volvo", "S80", 18000));
        Garage.add(new Car(2020,"Volvo", "S60", 20000));
        Garage.add(new Car(2022,"Volvo", "S60", 23000));
        Garage.add(new Car(2006,"Suzuki", "Baleno", 3000));
        Garage.add(new Car(2019,"Suzuki", "Swift", 14000));

        System.out.println("Max cost of car: " + CarService.getMaxCostCar(Garage).getCost() +"\n"); // Max cost

        System.out.println("Min cost of car: " + CarService.getMinCostCar(Garage).getCost() + "\n");

        System.out.println("Get brand Volvo from list: ");
        CarService.toString(CarService.findBrandList("Volvo", Garage));

        System.out.println("Get model A4 of brand from list: ");
        CarService.toString(CarService.findModelList("A4", Garage));

        System.out.println("Get cars by price range from 5000 to 15000: ");
        CarService.toString(CarService.getListByPriceRange(5000, 15000, Garage));

        System.out.println("Sort list by price");
        CarService.toString(CarService.sortListByPrice(Garage));

        System.out.println("Sort list by brand");
        CarService.toString(CarService.sortListByBrand(Garage));

        JsonService.jsonFileWriter(Garage, new File("CarInfo.json"));
        System.out.println("The list saved to file CarInfo.json\n");

        System.out.println("The list is loading from file CarInfo.json");
        JsonService.jsonFileReader(new File("CarInfo.json"));

        System.out.println("Building JSON array with cars information:");
        System.out.println(JsonService.buildCarsJson(Garage));
    }
}