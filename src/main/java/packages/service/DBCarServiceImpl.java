package packages.service;

import packages.model.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBCarServiceImpl extends JDBCService implements CarService{

    private ArrayList<Car> getListCar(String print){            // String "print" to print or no result
        ArrayList<Car> carArrayList = new ArrayList<>();
        try {
            while (super.getRs().next()) {
                Car car = new Car();
                car.setYear(super.getRs().getInt("Year"));
                car.setBrand(super.getRs().getString("Brand"));
                car.setModel(super.getRs().getString("Model"));
                car.setCost(super.getRs().getInt("Cost"));
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
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Cost;");
        return getListCar("y");
    }

    @Override
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage ORDER BY Brand;");
        return getListCar("y");
    }

    public ArrayList <Car> getAllBase(String print){
        super.readDB("SELECT Year, Brand, Model, Cost FROM Garage;");
        return getListCar(print);
    }
}
