package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.Main;
import com.education.projects.cars.manager.carsmanager.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The class for service Car information in database
 */
public class DBCarServiceImpl implements CarService{

    /**
     * Gets a car objects list, according to the current result set of the database,
     * returns the list Car objects,
     * parameter toPrint needs to choose whether the list can be printed
     * @param toPrint is parameter to choose whether the list can be printed
     * @return the list Car objects
     */
    private ArrayList<Car> getListCar(boolean toPrint){
        ArrayList<Car> carArrayList = new ArrayList<>();
        try {
            while (DBPoolService.getInstance().getRs().next()) {
                Car car = new Car();
                car.setYear(DBPoolService.getInstance().getRs().getInt("Year"));
                car.setBrand(DBPoolService.getInstance().getRs().getString("Brand"));
                car.setModel(DBPoolService.getInstance().getRs().getString("Model"));
                car.setCost(DBPoolService.getInstance().getRs().getInt("Cost"));
                carArrayList.add(car);
            }
            if(toPrint) printCarList(carArrayList);     //print result of query
            System.out.println();
            return carArrayList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    /**
     * Gets the list of max cost Car objects from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getMaxCostCar(ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of min cost Car objects from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    /**
     * Finds the list of Car objects of the same brand from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar(true);
    }

    @Override
    /**
     * Finds the list of Car objects of the same model from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of Car objects ranked by price from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");
        return getListCar(true);
    }

    @Override
    /**
     * Get the list of Car objects sorted by price from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Cost;");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of Car objects sorted by brand from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        DBPoolService.getInstance().readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Brand;");
        return getListCar(true);
    }

    /**
     * Gets result set of any query from database
     * @param query is a string of sql script
     * @return the list of car objects
     */
    public ArrayList <Car> getQuery(String query){
        DBPoolService.getInstance().readDB(query);
        return getListCar(true);
    }
}
