package packages.service;

import packages.Main;
import packages.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The class for service Car information in database
 */
public class DBCarServiceImpl implements CarService{

    /**
     * the method for getting a car objects list, according to the current resul set of the database
     * @param toPrint is parameter to choose whether the list can be printed
     * @return the list Car objects
     */
    private ArrayList<Car> getListCar(boolean toPrint){
        ArrayList<Car> carArrayList = new ArrayList<>();
        try {
            while (Main.source.getRs().next()) {
                Car car = new Car();
                car.setYear(Main.source.getRs().getInt("Year"));
                car.setBrand(Main.source.getRs().getString("Brand"));
                car.setModel(Main.source.getRs().getString("Model"));
                car.setCost(Main.source.getRs().getInt("Cost"));
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
     * to get the list of max cost Car objects from database
     * @param list is null
     */
    public ArrayList<Car> getMaxCostCar(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    /**
     * to get the list of min cost Car objects from database
     * @param list is null
     */
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    /**
     * to find the list of Car objects of the same brand from database
     * @param list is null
     */
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar(true);
    }

    @Override
    /**
     * to find the list of Car objects of the same model from database
     * @param list is null
     */
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar(true);
    }

    @Override
    /**
     * to get the list of Car objects ranked by price from database
     * @param list is null
     */
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");
        return getListCar(true);
    }

    @Override
    /**
     * to get the list of Car objects sorted by price from database
     * @param list is null
     */
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Cost;");
        return getListCar(true);
    }

    @Override
    /**
     * to get the list of Car objects sorted by brand from database
     * @param list is null
     */
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Brand;");
        return getListCar(true);
    }

    /**
     * to get resul set of any query from database
     * @param query is a string of sql script
     * @return the list of car objects
     */
    public ArrayList <Car> getQuery(String query){
        Main.source.readDB(query);
        return getListCar(true);
    }
}
