package packages.service;

import packages.model.Car;

import java.util.ArrayList;

public interface CarService {
    Car getMaxCostCar(ArrayList<Car> list);
    Car getMinCostCar(ArrayList<Car> list);
    ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list);
    ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list);
    ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list);
    ArrayList<Car> sortListByPrice(ArrayList<Car> list);
    ArrayList<Car> sortListByBrand(ArrayList<Car> list) ;
    void printCarList(ArrayList<Car> list);
}
