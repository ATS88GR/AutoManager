package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The class for service Car information in database
 */
@Service
public class DBCarServiceImpl implements CarService{
    @Autowired
    private DBPoolService dbPoolService;

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
            while (dbPoolService.getRs().next()) {
                Car car = new Car();
                setFieldCar(car);
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
        dbPoolService.statementExeQuery("SELECT * FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of min cost Car objects from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
        dbPoolService.statementExeQuery("SELECT * FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");
        return getListCar(true);
    }

    @Override
    /**
     * Finds the list of Car objects of the same brand from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar(true);
    }

    @Override
    /**
     * Finds the list of Car objects of the same model from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of Car objects ranked by price from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");
        return getListCar(true);
    }

    @Override
    /**
     * Get the list of Car objects sorted by price from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        dbPoolService.statementExeQuery("SELECT * FROM Garage ORDER BY Cost;");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of Car objects sorted by brand from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        dbPoolService.statementExeQuery("SELECT * FROM Garage ORDER BY Brand;");
        return getListCar(true);
    }

    /**
     * Gets result set of any query from database
     * @param query is a string of sql script
     * @return the list of car objects
     */
    public ArrayList <Car> getQuery(String query){
        dbPoolService.statementExeQuery(query);
        return getListCar(true);
    }

    /**
     * Creates a new Car object information in the database, returns a Car object from database by id
     * @param car Car object to be added to the database
     * @return Car object information from database by id
     * @throws SQLException
     */
    public Car createAuto(Car car) throws SQLException{
        long id = 0;
        dbPoolService.statementExeQuery("INSERT INTO Garage (Year, Brand, Model, Cost)" +
                " VALUES(" + car.getYear()+ ", '" + car.getBrand() + "', '" + car.getModel() +
                "', " + car.getCost() +") RETURNING id;");
        dbPoolService.getRs().next();
        id = dbPoolService.getRs().getInt("Id");
        return getCarById(id);
    }

    /**
     * Updates the Car object information by id with update car information
     * @param car Car object information to update
     * @param id id of the car object to be updated
     * @return Car object information from database by id
     * @throws Exception
     */
    public Car updateAuto(Car car, Long id) throws Exception{
        dbPoolService.statementExeQuery("UPDATE Garage SET Year = " + car.getYear() + ", Brand = '"+
                car.getBrand() + "', Model = '" + car.getModel() + "', Cost = " + car.getCost() +
                " WHERE Id = " + id +";");
        return getCarById(id);
    }

    /**
     * Gets all cars objects information from database
     * @return The list of the Car objects
     */
    public Collection<Car> getAllCars() {
        dbPoolService.statementExeQuery("SELECT * FROM Garage;");
        return getListCar(false);
    }

    /**
     * Gets the Car object information from the database by id
     * @param id id of the car object in database
     * @return The Car object from database
     * @throws SQLException
     */
    public Car getCarById(Long id) throws SQLException {
        Car car;
        dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Id =" + id +";");
        dbPoolService.getRs().next();
        car = new Car();
        setFieldCar(car);
        return car;
    }

    /**
     * Sets fields of Car objects by result set information from database
     * @param car The Car object to set fields
     * @throws SQLException
     */
    private void setFieldCar(Car car) throws SQLException {
        car.setId(dbPoolService.getRs().getInt("Id"));
        car.setYear(dbPoolService.getRs().getInt("Year"));
        car.setBrand(dbPoolService.getRs().getString("Brand"));
        car.setModel(dbPoolService.getRs().getString("Model"));
        car.setCost(dbPoolService.getRs().getInt("Cost"));
    }

    /**
     * Removes the row with id from database
     * @param id is a row in database
     */
    public void deleteCarById(Long id) {
        dbPoolService.statementExe("DELETE FROM Garage WHERE Id = " + id + ";");
    }

    /**
     * Sorts and filters Cars objects information from database, returns list of Car objects
     * @param sortBy Sets the sort order
     * @param sortDirection Sets the sort direction (ACK/DESC)
     * @param filter The filter parameter, which need to parse
     * @return The list of the Car objects
     * @throws SQLException
     */
    public Collection<Car> getSortedFilteredCars(String sortBy, String sortDirection, String filter)
            throws SQLException {
        dbPoolService.statementExeQuery("SELECT * FROM Garage" + parseFilter(filter) +
                " ORDER BY " + sortBy + " " + sortDirection +";");
        return getListCar(false);
    }

    /**
     * Parses the filter information
     * @param filter The filter information
     * @return SQL script to filtering the database
     */
    private String parseFilter(String filter) {
        if (filter == null || filter.equals("")) return "";
        else {
            String[] arrFilter = filter.split("\\.");
            return " WHERE " + arrFilter[1] + " " + ((arrFilter[0].equals("eq"))? "= "  : "!= " + arrFilter[2]);
        }
    }
}
