package packages;

import packages.model.Car;
import packages.service.CarServiceImpl;
import packages.service.JsonServiceImpl;
import packages.utils.TestCar;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> garage = TestCar.getGarage();
        CarServiceImpl carService = new CarServiceImpl();

        System.out.println("Max cost of car: " + carService.getMaxCostCar(garage).getCost() +"\n");

        System.out.println("Min cost of car: " + carService.getMinCostCar(garage).getCost() + "\n");

        System.out.println("Get brand Volvo from list: ");
        carService.printCarList(carService.findBrandList("Volvo", garage));

        System.out.println("Get model A4 of brand from list: ");
        carService.printCarList(carService.findModelList("A4", garage));

        System.out.println("Get cars by price range from 5000 to 15000: ");
        carService.printCarList(carService.getListByPriceRange(5000, 15000, garage));

        System.out.println("Sort list by price");
        carService.printCarList(carService.sortListByPrice(garage));

        System.out.println("Sort list by brand");
        carService.printCarList(carService.sortListByBrand(garage));

        JsonServiceImpl jsonService = new JsonServiceImpl();

        jsonService.jsonFileWriter(garage, new File("CarInfo.json"));
        System.out.println("The list saved to file CarInfo.json\n");

        System.out.println("The list is loading from file CarInfo.json");
        jsonService.jsonFileReader(new File("CarInfo.json"));
        System.out.println();

        System.out.println("Building JSON array with cars information:");
        System.out.println(jsonService.buildCarsJson(garage));
    }
}