package packages.service;

import packages.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBCarServiceImpl extends JDBCService implements CarService{

    private ArrayList<Car> getListCar(String print){    //method for get car list of result set database
        ArrayList<Car> carArrayList = new ArrayList<>();
        try {
            while (getRs().next()) {
                Car car = new Car();
                car.setYear(getRs().getInt("Year"));
                car.setBrand(getRs().getString("Brand"));
                car.setModel(getRs().getString("Model"));
                car.setCost(getRs().getInt("Cost"));
                carArrayList.add(car);
            }
            if(print.equals("y")) printCarList(carArrayList);     //print result of query
            System.out.println();
            return carArrayList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Car> getMaxCostCar(ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Cost;");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Brand;");
        return getListCar("y");
    }

    public ArrayList <Car> getQuery(String query){
        readDB(query);
        return getListCar("y");
    }
}
