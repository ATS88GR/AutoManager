package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.entity.Car;
import com.education.projects.cars.manager.carsmanager.repository.CarRepository;
import com.education.projects.cars.manager.carsmanager.utils.CarSpecification;
import com.education.projects.cars.manager.carsmanager.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The class for service Car information in database
 */
@Service
public class DBCarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    /**
     * Gets a car objects list, according to the current result set of the database,
     * returns the list Car objects,
     * parameter toPrint needs to choose whether the list can be printed
     *
     * @param toPrint is parameter to choose whether the list can be printed
     * @return the list Car objects
     */
    private ArrayList<Car> getListCar(boolean toPrint) {
        /*ArrayList<Car> carArrayList = new ArrayList<>();
        try {
            while (carRepository.getRs().next()) {
                Car car = new Car();
                setFieldCar(car);
                carArrayList.add(car);
            }
            if(toPrint) printCarList(carArrayList);     //print result of query
            System.out.println();
            return carArrayList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        return null;
    }

    @Override
    /**
     * Gets the list of max cost Car objects from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getMaxCostCar(ArrayList<Car> list) {
        /*dbPoolService.statementExeQuery("SELECT * FROM Garage " +
        "where cost = (select Max(Cost) from Garage);");*/
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of min cost Car objects from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getMinCostCar(ArrayList<Car> list) {
   /*     dbPoolService.statementExeQuery("SELECT * FROM Garage " +
                "where cost = (select Min(Cost) from Garage);");*/
        return getListCar(true);
    }

    @Override
    /**
     * Finds the list of Car objects of the same brand from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> findBrandList(String searchBrand, ArrayList<Car> list) {
        //dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Brand = '" + searchBrand +"';");
        return getListCar(true);
    }

    @Override
    /**
     * Finds the list of Car objects of the same model from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> findModelList(String searchModel, ArrayList<Car> list) {
        //dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Model = '" + searchModel +"';");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of Car objects ranked by price from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> getListByPriceRange(int startPrice, int endPrice, ArrayList<Car> list) {
        /*dbPoolService.statementExeQuery("SELECT * FROM Garage WHERE Cost BETWEEN "
                + startPrice + " AND " + endPrice +";");*/
        return getListCar(true);
    }

    @Override
    /**
     * Get the list of Car objects sorted by price from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> sortListByPrice(ArrayList<Car> list) {
        //dbPoolService.statementExeQuery("SELECT * FROM Garage ORDER BY Cost;");
        return getListCar(true);
    }

    @Override
    /**
     * Gets the list of Car objects sorted by brand from database, parameter list is null
     * @param list is null
     */
    public ArrayList<Car> sortListByBrand(ArrayList<Car> list) {
        //dbPoolService.statementExeQuery("SELECT * FROM Garage ORDER BY Brand;");
        return getListCar(true);
    }

    /**
     * Creates a new Car object information in the database, returns a Car object from database by id
     *
     * @param car Car object to be added to the database
     * @return Car object information from database by id
     * @throws SQLException
     */
    public Car createAuto(Car car) throws SQLException {
        return carRepository.save(car);
    }

    /**
     * Updates the Car object information by id with update car information
     *
     * @param car Car object information to update
     * @param id  id of the car object to be updated
     * @return Car object information from database by id
     * @throws Exception
     */
    public Car updateAuto(Car car, Integer id) throws Exception {
        Car carToChange = carRepository.getReferenceById(id);
        car.setId(carToChange.getId());
        return carRepository.save(car);
    }

    /**
     * Gets all cars objects information from database
     *
     * @return The list of the Car objects
     */
    public Collection<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Gets the Car object information from the database by id
     *
     * @param id id of the car object in database
     * @return The Car object from database
     * @throws SQLException
     */
    public Car getCarById(Integer id) throws SQLException {
        return carRepository.getReferenceById(id);
    }

    /**
     * Removes the row with id from database
     *
     * @param id is a row in database
     */
    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }

    /**
     * Sorts and filters Cars objects information from database, returns list of Car objects
     *
     * @param sortBy        Sets the sort order
     * @param sortDirection Sets the sort direction (ACK/DESC)
     * @param filter        The filter parameter, which need to parse
     * @return The list of the Car objects
     * @throws SQLException
     */
    public Collection<Car> getSortedFilteredCars(String sortBy, String sortDirection, String filter)
            throws SQLException {
        String key;
        String operation;
        String value;

        String[] arrFilter = filter.split("\\.");
        key = arrFilter[1];
        value = arrFilter[2];
        operation = (arrFilter[0].equals("eq")) ? "= " : "!= ";

        CarSpecification spec = new CarSpecification(new SearchCriteria(key, operation, value));
        return carRepository.findAll(spec);
    }
}
