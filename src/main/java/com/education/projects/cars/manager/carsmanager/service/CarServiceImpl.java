package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.entity.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CarServiceImpl implements CarService {

    public ArrayList<Car> getMaxCostCar(ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        int maxCost =0;
        for (Car car: list)
            if(car.getCost()>maxCost)
                maxCost = car.getCost();
        for (Car car: list)
            if(car.getCost() == maxCost) result.add(car);
        printCarList(result);
        System.out.println();
        return result;
    }
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        int minCost = list.get(0).getCost();
        for (Car car: list)
            if(car.getCost()<minCost)
                minCost = car.getCost();
        for (Car car: list)
            if(car.getCost() == minCost) result.add(car);
        printCarList(result);
        System.out.println();
        return result;
    }
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        for(Car car:list)
            if(car.getBrand().equals(searchBrand)) result.add(car);
        printCarList(result);
        return result;
    }
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        for(Car car:list)
            if(car.getModel().equals(searchModel)) result.add(car);
        printCarList(result);
        System.out.println();
        return result;
    }
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list){
        ArrayList<Car> result = new ArrayList<>();
        for(Car car: list)
            if(car.getCost()>=startPrice && car.getCost()<=endPrice) result.add(car);
        printCarList(result);
        System.out.println();
        return result;
    }
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list){
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
        printCarList(list);
        System.out.println();
        return list;
    }
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
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
        printCarList(list);
        System.out.println();
        return list;
    }

    /**
     * Replaces elements in Car objects list between index1 and index2
     * @param list
     * @param index1
     * @param index2
     * @return Car objects list
     */
    private ArrayList<Car> replaceElements(ArrayList<Car> list, int index1, int index2){
        Car tempCar = list.get(index2);
        list.set((index2), list.get(index1));
        list.set(index1, tempCar);
        return list;
    }
}
