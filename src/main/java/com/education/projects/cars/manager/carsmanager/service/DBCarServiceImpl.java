package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import com.education.projects.cars.manager.carsmanager.entity.CarPage;
import com.education.projects.cars.manager.carsmanager.entity.CarSearchCriteria;
import com.education.projects.cars.manager.carsmanager.mapper.CarMapperReq;
import com.education.projects.cars.manager.carsmanager.mapper.CarMapperResp;
import com.education.projects.cars.manager.carsmanager.repository.CarRepository;
import com.education.projects.cars.manager.carsmanager.repository.CarSpecification;
import com.education.projects.cars.manager.carsmanager.repository.CarCriteriaRepository;
import com.education.projects.cars.manager.carsmanager.repository.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * The class for service Car information in database
 */
@Service
public class DBCarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarCriteriaRepository carCriteriaRepository;

    /**
     * Creates a new Car object information in the database, returns a Car object from database by id
     *
     * @param carDtoReq Car object to be added to the database
     * @return Car object information from database by id
     * @throws SQLException
     */
    public CarDtoResp createAuto(CarDtoReq carDtoReq) throws SQLException {
        return CarMapperResp.INSTANCE.carToCarDto(
                carRepository.save(CarMapperReq.INSTANCE.carDtoToCar(carDtoReq)));
    }

    /**
     * Updates the Car object information by id with update car information
     *
     * @param carDtoReq Car object information to update
     * @param id  id of the car object to be updated
     * @return Car object information from database by id
     * @throws SQLException
     */
    public CarDtoResp updateAuto(CarDtoReq carDtoReq, Integer id) throws SQLException {
        if(carRepository.existsById(id)) {
            Car carToChange = CarMapperReq.INSTANCE.carDtoToCar(carDtoReq);
            carToChange.setId(id);
            return CarMapperResp.INSTANCE.carToCarDto(carRepository.save(carToChange));
        } else {
            throw new SQLException("The car with id = " + id +" wasn't found");
        }

    }

    /**
     * Gets all cars objects information from database
     *
     * @return The list of the Car objects
     */
    public List<CarDtoResp> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarMapperResp.INSTANCE::carToCarDto).toList();
    }

    /**
     * Gets the Car object information from the database by id
     *
     * @param id id of the car object in database
     * @return The Car object from database
     * @throws SQLException
     */
    public CarDtoResp getCarById(Integer id) throws SQLException {
        if(carRepository.existsById(id))
            return CarMapperResp.INSTANCE.carToCarDto(carRepository.getReferenceById(id));
        else
            throw new SQLException("The car with id = " + id +" wasn't found");
    }

    /**
     * Removes the row with id from database
     *
     * @param id is a row in database
     */
    public void deleteCarById(Integer id) throws SQLException {
        if(carRepository.existsById(id))
            carRepository.deleteById(id);
        else throw new SQLException("The car with id = " + id +" wasn't found");
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

        String[] arrFilter = filter.split("\\.");
        String key = arrFilter[1];
        String value = arrFilter[2];
        String operation = (arrFilter[0].equals("eq")) ? "= " : "!= ";

        CarSpecification spec = new CarSpecification(new SearchCriteria(key, operation, value));
        return carRepository.findAll(spec);
    }

    public Page<CarDtoResp> getSortedFilteredCarsCommon(CarPage carPage,
                                  CarSearchCriteria carSearchCriteria){
        return carCriteriaRepository.findAllWithFilters(carPage, carSearchCriteria);
    }
}
