package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        System.out.println("Max cost of car: " + CarService.MaxCost(Garage).getCost() +"\n"); // Max cost

        System.out.println("Min cost of car: " + CarService.MinCost(Garage).getCost() + "\n");

        System.out.println("Get brand Volvo from list: ");
        CarService.toString(CarService.getListByBrand("Volvo", Garage));

        System.out.println("Get model A4 of brand from list: ");
        CarService.toString(CarService.getListByModel("A4", Garage));

        System.out.println("Get cars by price range from 5000 to 15000: ");
        CarService.toString(CarService.getListByPriceRange(5000, 15000, Garage));

        System.out.println("Sort list by price");
        CarService.toString(CarService.sortListByPrice(Garage));

        System.out.println("Sort list by brand");
        CarService.toString(CarService.sortListByBrand(Garage));

        saveToFile(Garage);
        System.out.println("The list saved to file Report.bin\n");

        System.out.println("The list is loading from file Report");
        CarService.toString(loadFromFile());

        System.out.println("Building JSON array with cars information:");
        System.out.println(JsonService.buildCarsJson(Garage));

    }

    public static void saveToFile(ArrayList<Car> list){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "\\Report.bin"))){
            oos.writeObject(list); // Write object in file
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Car> loadFromFile(){
        ArrayList<Car> tempList = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "\\Report.bin"))) {
            tempList = (ArrayList<Car>) ois.readObject(); // read List
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tempList;
    }
}