package packages.service;

import packages.model.Car;

import java.util.ArrayList;

public interface CarService {
    ArrayList<Car> getMaxCostCar(ArrayList<Car> list);
    ArrayList<Car> getMinCostCar(ArrayList<Car> list);
    ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list);
    ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list);
    ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list);
    ArrayList<Car> sortListByPrice(ArrayList<Car> list);
    ArrayList<Car> sortListByBrand(ArrayList<Car> list) ;
    default void printCarList(ArrayList<Car> list){
        list.forEach(car -> {
            String strF = String.format("Year: %d, brand: %s, model: %s, cost: %d\n",
                    car.getYear(), car.getBrand(), car.getModel(), car.getCost());
            System.out.print(strF);
        });
        System.out.println();
        /*for (Car car: list)
            System.out.println(car.getYear() + " " + car.getBrand() + " " + car.getModel() + " " + car.getCost());
        System.out.println();*/
    }
}
