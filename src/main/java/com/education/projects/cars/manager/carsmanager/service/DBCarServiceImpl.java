package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import com.education.projects.cars.manager.carsmanager.entity.CarPage;
import com.education.projects.cars.manager.carsmanager.entity.CarSearchCriteria;
import com.education.projects.cars.manager.carsmanager.mapper.CarMapper;
import com.education.projects.cars.manager.carsmanager.repository.CarRepository;
import com.education.projects.cars.manager.carsmanager.repository.CarSpecification;
import com.education.projects.cars.manager.carsmanager.repository.CarCriteriaRepository;
import com.education.projects.cars.manager.carsmanager.repository.SearchCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * The class for service Car information in database
 */
@Service
@Slf4j
public class DBCarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarCriteriaRepository carCriteriaRepository;
    @Autowired
    private CarMapper carMapper;

    /**
     * Creates a new Car object information in the database, returns a Car object from database by id
     *
     * @param carDtoReq Car object to be added to the database
     * @return Car object information from database by id
     * @throws Exception
     */
    public CarDtoResp createAuto(CarDtoReq carDtoReq) throws Exception {
        try {
            return carMapper.carToCarDto(
                    carRepository.save(carMapper.carDtoToCar(carDtoReq)));
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Updates the Car object information by id with update car information
     *
     * @param carDtoReq Car object information to update
     * @param id  id of the car object to be updated
     * @return Car object information from database by id
     * @throws Exception
     */
    public CarDtoResp updateAuto(CarDtoReq carDtoReq, Integer id) throws Exception {
        try {
            if (carRepository.existsById(id)) {
                Car carToChange = carMapper.carDtoToCar(carDtoReq);
                carToChange.setId(id);
                return carMapper.carToCarDto(carRepository.save(carToChange));
            } else {
                Exception e = new Exception("The car wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception ex){
            log.error("Error: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Gets all cars objects information from database
     *
     * @return The list of the Car objects
     */
    public Collection<CarDtoResp> getAllCars() throws Exception{
        try {
            return carMapper.carListToCarDtoList(carRepository.findAll());
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Gets the Car object information from the database by id
     *
     * @param id id of the car object in database
     * @return The Car object from database
     * @throws Exception
     */
    public CarDtoResp getCarById(Integer id) throws Exception {
        try {
            if (carRepository.existsById(id))
                return carMapper.carToCarDto(carRepository.getReferenceById(id));
            else {
                Exception e = new Exception("The car wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Removes the row with id from database
     *
     * @param id is a row in database
     */
    public void deleteCarById(Integer id) throws Exception {
        try {
            if (carRepository.existsById(id))
                carRepository.deleteById(id);
            else {
                Exception e = new Exception("The car wasn't found");
                log.error("Error: {}", e.getMessage());
                throw e;
            }
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Sorts and filters Cars objects information from database, returns list of Car objects
     *
     * @param sortBy        Sets the sort order
     * @param sortDirection Sets the sort direction (ACK/DESC)
     * @param filter        The filter parameter, which need to parse
     * @return The list of the Car objects
     * @throws Exception
     */
    public Collection<Car> getSortedFilteredCars(String sortBy, String sortDirection, String filter)
            throws Exception {

        String[] arrFilter = filter.split("\\.");
        String key = arrFilter[1];
        String value = arrFilter[2];
        String operation = (arrFilter[0].equals("eq")) ? "= " : "!= ";

        CarSpecification spec = new CarSpecification(new SearchCriteria(key, operation, value));
        return carRepository.findAll(spec);
    }

    public Page<CarDtoResp> getSortedFilteredCarsCommon(CarPage carPage,
                                  CarSearchCriteria carSearchCriteria)
    throws Exception{
        try {
            return carCriteriaRepository.findAllWithFilters(carPage, carSearchCriteria);
        }catch (Exception e){
            log.error("Error: {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
