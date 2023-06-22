package packages.service;

import packages.Main;
import packages.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBCarServiceImpl implements CarService{

    private ArrayList<Car> getListCar(boolean toPrint){    //method for get car list of result set database
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
    public ArrayList<Car> getMaxCostCar(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar(true);
    }

    @Override
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar(true);
    }

    @Override
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");
        return getListCar(true);
    }

    @Override
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Cost;");
        return getListCar(true);
    }

    @Override
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        Main.source.readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Brand;");
        return getListCar(true);
    }

    public ArrayList <Car> getQuery(String query){
        Main.source.readDB(query);
        return getListCar(true);
    }
}
