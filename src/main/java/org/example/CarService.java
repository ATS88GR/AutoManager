package org.example;

import java.util.ArrayList;

public class CarService {
    public static Car MaxCost(ArrayList<Car> list){
        int maxCost =0;
        Car result = null;
        for (Car car: list) {
            if(car.getCost()>maxCost) {
                result = car;
                maxCost = car.getCost();
            }
        }
        return result;
    }
    public static Car MinCost(ArrayList<Car> list){
        int minCost = list.get(0).getCost();
        Car result = null;
        for (Car car: list) {
            if(car.getCost()<minCost) {
                result = car;
                minCost = car.getCost();
            }
        }
        return result;
    }
    public static ArrayList<Car> getListByBrand(String searchBrand, ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        for(Car car:list)
            if(car.getBrand().equals(searchBrand)) result.add(car);
        return result;
    }
    public static ArrayList<Car> getListByModel(String searchModel, ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        for(Car car:list)
            if(car.getModel().equals(searchModel)) result.add(car);
        return result;
    }
    public static ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        for(Car car: list)
            if(car.getCost()>startPrice && car.getCost()<endPrice) result.add(car);
        return result;
    }
    public static ArrayList<Car> sortListByPrice(ArrayList<Car> list){
        for (int j = 0; j< list.size()/2; j++) {
            for (int i = j; i < list.size() - 1-j; i++) {
                if (list.get(i).getCost() > list.get(i + 1).getCost())
                    replaceElements(list, i, i + 1);
            }
            for (int i = j; i < list.size() - 1-j; i++) {
                if (list.get(list.size()-2-i).getCost() > list.get(list.size()-1-i).getCost())
                    replaceElements(list, list.size() - 2 - i, list.size() - 1 - i);
            }
        }
        return list;
    }
    public static ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        Car tempCar;
        for (int j = 0; j< list.size()/2; j++) {
            for (int i = j; i < list.size() - 1-j; i++) {
                if (list.get(i).getBrand().toLowerCase().charAt(0) > list.get(i+1).getBrand().toLowerCase().charAt(0))
                    replaceElements(list,i, i+1);
                // if first char is equal, check all characters
                else if(list.get(i).getBrand().toLowerCase().charAt(0) == list.get(i+1).getBrand().toLowerCase().charAt(0)){
                    int minLength = Math.min(list.get(i).getBrand().length(), list.get(i + 1).getBrand().length());
                    for (int k = 1; k<minLength; k++){
                        if(list.get(i).getBrand().toLowerCase().charAt(k) > list.get(i+1).getBrand().toLowerCase().charAt(k)){
                            replaceElements(list,i, i+1);
                            break;
                        }else if(list.get(i).getBrand().toLowerCase().charAt(k) != list.get(i+1).getBrand().toLowerCase().charAt(k)) break;
                    }
                }
            }
            for (int i = j; i < list.size() - 1-j; i++) {
                if (list.get(list.size()-2-i).getBrand().toLowerCase().charAt(0) > list.get(list.size()-1-i).getBrand().toLowerCase().charAt(0))
                    replaceElements(list, list.size() - 2 - i, list.size() - 1 - i);
                    // if first char is equal, check all characters
                else if(list.get(list.size()-2-i).getBrand().toLowerCase().charAt(0) == list.get(list.size()-1-i).getBrand().toLowerCase().charAt(0)) {
                    int minLength = Math.min(list.get(list.size()-2-i).getBrand().length(), list.get(list.size()-1-i).getBrand().length());
                    for (int k = 1; k < minLength; k++) {
                        if (list.get(list.size()-2-i).getBrand().toLowerCase().charAt(k) > list.get(list.size()-1-i).getBrand().toLowerCase().charAt(k)) {
                            replaceElements(list, list.size() - 2 - i, list.size() - 1 - i);
                            break;
                        } else if (list.get(list.size()-2-i).getBrand().toLowerCase().charAt(k) != list.get(list.size()-1-i).getBrand().toLowerCase().charAt(k)) break;
                    }
                }
            }
        }
        return list;
    }
    private static ArrayList<Car> replaceElements(ArrayList<Car> list, int index1, int index2){
        Car tempCar = list.get(index2);
        list.set((index2), list.get(index1));
        list.set(index1, tempCar);
        return list;
    }
    public static void toString(ArrayList<Car> list){
        for (Car car: list)
            System.out.println(car.getYear() + " " + car.getBrand() + " " + car.getModel() + " " + car.getCost());
        System.out.println();
    }

}
