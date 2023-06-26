package packages.service;

import packages.model.Car;

import java.util.ArrayList;

/**
 * The interface for service Car objects information
 */
public interface CarService {
    /**
     * to get max cost Car objects from
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> getMaxCostCar(ArrayList<Car> list);

    /**
     * to get min cost Car objects from
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> getMinCostCar(ArrayList<Car> list);
    /**
     * to find Car objects with brand
     * @param searchBrand from
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list);

    /**
     * to find Car objects with model
     * @param searchModel from
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list);

    /**
     * to get Car objects by price range from start price
     * @param startPrice to end price
     * @param endPrice from list
     * @param list Car objects
     * @return list Car objects
     */
    ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list);

    /**
     * to sort list
     * @param list Car objects by price
     * @return list Car objects
     */
    ArrayList<Car> sortListByPrice(ArrayList<Car> list);

    /**
     * to sort list
     * @param list Car objects by price
     * @return list Car objects
     */
    ArrayList<Car> sortListByBrand(ArrayList<Car> list) ;

    /**
     * to print list
     * @param list Car objects
     */
    default void printCarList(ArrayList<Car> list){
        list.forEach(car -> {
            String strF = String.format("Year: %d, brand: %s, model: %s, cost: %d\n",
                    car.getYear(), car.getBrand(), car.getModel(), car.getCost());
            System.out.print(strF);
        });
    }
}
