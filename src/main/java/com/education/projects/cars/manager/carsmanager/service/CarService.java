package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.model.Car;

import java.util.ArrayList;

/**
 * The interface for service Car objects information
 */
public interface CarService {
    /**
     * Gets max cost Car objects from list Car objects, returns list Car objects
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> getMaxCostCar(ArrayList<Car> list);

    /**
     * Gets min cost Car objects from list Car objects, return list Car objects
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> getMinCostCar(ArrayList<Car> list);
    /**
     * Finds Car objects with brand searchBrand from list Car objects, returns list Car objects
     * @param searchBrand it is a string of search brand
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list);

    /**
     * Finds Car objects with model searchModel from list Car objects, returns list Car objects
     * @param searchModel from
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list);

    /**
     * Gets Car objects by price range from start price startPrice to end price endPrice from list
     * of Car objects, returns Car objects
     * @param startPrice int of start price
     * @param endPrice int of end price
     * @param list Car objects list
     * @return list Car objects
     */
    ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list);

    /**
     * Sorts Car objects list by price, returns Car objects list
     * @param list Car objects list
     * @return Car objects list
     */
    ArrayList<Car> sortListByPrice(ArrayList<Car> list);

    /**
     * Sorts Car objects list by brand, returns Car objects list
     * @param list Car objects list
     * @return Car objects list
     */
    ArrayList<Car> sortListByBrand(ArrayList<Car> list) ;

    /**
     * Prints Car objects list
     * @param list Car objects list
     */
    default void printCarList(ArrayList<Car> list){
        list.forEach(car -> {
            String strF = String.format("Id: %d, year: %d, brand: %s, model: %s, cost: %d\n",
                    car.getId(), car.getYear(), car.getBrand(), car.getModel(), car.getCost());
            System.out.print(strF);
        });
    }
}
