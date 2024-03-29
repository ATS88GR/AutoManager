package com.education.projects.cars.manager.carsmanager.controller;

import com.education.projects.cars.manager.carsmanager.dto.request.CarDtoReq;
import com.education.projects.cars.manager.carsmanager.dto.response.CarDtoResp;
import com.education.projects.cars.manager.carsmanager.entity.Car;
import com.education.projects.cars.manager.carsmanager.entity.CarPage;
import com.education.projects.cars.manager.carsmanager.entity.CarSearchCriteria;
import com.education.projects.cars.manager.carsmanager.service.DBCarServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Validated
@Slf4j
@Tag(name = "Cars API")
public class AutoController {

    @Autowired
    private DBCarServiceImpl dbCarServiceImpl;

    @Operation(summary = "Creates new row in database with car information",
            description = "Returns created car information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PostMapping("/cars")  //url
    public ResponseEntity<CarDtoResp> createCar (@Valid @RequestBody CarDtoReq carDtoReq)
            throws Exception{
        log.info("Create car = {}", carDtoReq);
        return new ResponseEntity<> (dbCarServiceImpl.createAuto(carDtoReq), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates car information by id",
            description = "Returns updated car information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The car was not found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @PutMapping("/cars/{id}")
    public ResponseEntity <CarDtoResp> updateCar (
            @Valid @RequestBody CarDtoReq carDtoReq, @PathVariable ("id") @NotNull @Min(1) Integer id)
    throws Exception{
        log.info("Update car with id = {}, update car info {}", id, carDtoReq);
        return new ResponseEntity<>(dbCarServiceImpl.updateAuto(carDtoReq, id), HttpStatus.OK);
    }

    @Operation(summary = "Gets information about all cars from database",
            description = "Returns collection of car objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/cars")
    public ResponseEntity <Collection<CarDtoResp>> getCars() throws Exception {
        log.info("Get all car info");
        return new ResponseEntity <> (dbCarServiceImpl.getAllCars(), HttpStatus.OK);
    }

    @Operation(summary = "Gets sorted and filtered information about cars from database",
            description = "Returns collection of car objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/sortedCars")
    public Collection <Car> getSortFilterCars(
            @Schema(name = "sortBy", description = "sorting column", example = "year")
            @RequestParam String sortBy,
            @Schema(name = "sortDirection", description = "direction of sorting", example = "ASC/DESC")
            @RequestParam String sortDirection,
            @Schema(name = "filter", description = "filtering settings", example = "not_eq.year.2012")
            @RequestParam (required = false) String filter
    )
    throws Exception{
        log.info("Get sorted and filtered car info");
        return dbCarServiceImpl.getSortedFilteredCars(sortBy, sortDirection, filter);
    }

    @Operation(summary = "Gets sorted and filtered information about cars from database",
            description = "Returns collection of car objects from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/sortedFilteredCars")
    public ResponseEntity<Page<CarDtoResp>> getSortFilterCarsCommon(CarPage carPage,
                                                       CarSearchCriteria carSearchCriteria)
    throws Exception{
        log.info("Get common sorted and filtered car info");
        return new ResponseEntity<>(dbCarServiceImpl.getSortedFilteredCarsCommon(carPage, carSearchCriteria),
                HttpStatus.OK);
    }

    @Operation(summary = "Gets car by id",
            description = "Returns id car information from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found - The car was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDtoResp> getCarById(@PathVariable ("id") @NotNull @Min(1) Integer id)
    throws Exception{
        log.info("Gets car with id = {}", id);
        return new ResponseEntity <> (dbCarServiceImpl.getCarById(id), HttpStatus.OK);
    }

    @Operation(summary = "Deletes car by id",
            description = "Removes the row with id from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found - The car was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable ("id") @NotNull @Min(1) Integer id)
            throws Exception {
        log.info("Deletes car with id = {}", id);
        dbCarServiceImpl.deleteCarById(id);
        return new ResponseEntity<>("The car deleted", HttpStatus.OK);
    }
}
