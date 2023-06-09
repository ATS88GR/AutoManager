package com.education.projects.cars.manager.carsmanager.service;

import com.education.projects.cars.manager.carsmanager.model.Car;
import com.education.projects.cars.manager.carsmanager.utils.TestCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 *The class provides a dialog menu with service calls
 */
@Service
public class DialogMenuService {
    @Autowired
    private ScannerService scannerService;
    @Autowired
    @Qualifier("jsonReadWriteServiceImpl")
    private ReadWriteService jsonRWService;
    @Autowired
    @Qualifier("txtReadWriteServiceImpl")
    private ReadWriteService txtRWService;
    @Autowired
    @Qualifier("carServiceImpl")
    private CarService carServiceImpl;
    @Autowired
    @Qualifier("DBCarServiceImpl")
    private CarService dbCarServiceImpl;
    @Autowired
    private DBPoolService dbPoolService;

    /**
     *Shows actions in start menu and transmit saving choose to actions() method
     */
    public void chooseAction() {
        int chooseAct=4;   //int for saving choose
        try {
            do {            //to loop until choose exit number
                System.out.println("""
                        Select the number of service:
                        1. Data base.
                        2. JSON.
                        3. TXT.
                        4. Exit.
                        """);
                if (scannerService.getSc().hasNextLine()) {
                    chooseAct = Integer.parseInt(scannerService.getSc().nextLine());
                    actions(chooseAct);
                }
            }while (chooseAct != 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     *Serves chooseAction method to switch between choosing actions
     * @param act for saving choose from start menu
     */
    private void actions(int act) {
        switch (act) {
            case 1 -> dbMenu();   //dialog menu database
            case 2 -> jsonMenu(); //dialog menu json
            case 3 -> txtMenu();  //dialog menu txt
            case 4 -> System.out.println("Exit the program\n");
            default -> System.out.println("You choose number without action\n");
        }
    }
    /**
     * Contains common actions for database, json, txt
     * @param carService for implemented CarService interface classes
     * @param rwService for implemented ReadWriteService interface classes
     * @param carList it is the ArrayList of the Car objects
     */
    private void commonActions(CarService carService, ReadWriteService rwService, ArrayList <Car> carList){
        int chooseAct = 11;
        do {
            System.out.println("""
                    Select the number of action:
                    1. Find max cost car.
                    2. Find min cost car.
                    3. Get cars of the same brand.
                    4. Get cars of the same model.
                    5. Get cars by price range.
                    6. Sort cars by price.
                    7. Sort cars by brand.
                    8. Write cars info to txt file.
                    9. Write cars info to json file.
                    10. Show cars info from chosen service (file/database).
                    11. Exit.
                    """);
            if (scannerService.getSc().hasNextLine()) {
                chooseAct = Integer.parseInt(scannerService.getSc().nextLine());
                switch (chooseAct) {
                    case 1 -> carService.getMaxCostCar(carList);
                    case 2 -> carService.getMinCostCar(carList);
                    case 3 -> {
                        System.out.println("Write the brand of car to search:");
                        if(scannerService.getSc().hasNextLine())
                            carService.findBrandList(scannerService.getSc().nextLine(), carList);
                    }
                    case 4 -> {
                        System.out.println("Write the model of car to search:");
                        if(scannerService.getSc().hasNextLine())
                            carService.findModelList(scannerService.getSc().nextLine(), carList);
                    }
                    case 5 -> {
                        System.out.println("Write min price:");
                        int minPrice = Integer.parseInt(scannerService.getSc().nextLine());
                        System.out.println("Write max price:");
                        int maxPrice = Integer.parseInt(scannerService.getSc().nextLine());
                        carService.getListByPriceRange(minPrice, maxPrice, carList);
                    }
                    case 6 -> carService.sortListByPrice(carList);
                    case 7 -> carService.sortListByBrand(carList);
                    case 8 -> {
                        System.out.println("Write txt file name to save:");
                        if (scannerService.getSc().hasNextLine()) {
                            if(carList == null) txtRWService.fileWriter(carService.sortListByPrice(null), scannerService.getSc().nextLine());
                            else txtRWService.fileWriter(carList, scannerService.getSc().nextLine());
                        }
                    }
                    case 9 -> {
                        System.out.println("Write json file name to save:");
                        if (scannerService.getSc().hasNextLine()) {
                            if(carList == null) jsonRWService.fileWriter(carService.sortListByPrice(null), scannerService.getSc().nextLine());
                            else jsonRWService.fileWriter(carList, scannerService.getSc().nextLine());
                        }
                    }
                    case 10 -> {
                        if(carList == null) carService.sortListByPrice(null);
                        else {
                            System.out.println("Write file name to read:");
                            rwService.fileReader(scannerService.getSc().nextLine());
                        }
                    }
                    case 11 -> System.out.println("Exit from menu\n");
                    default -> System.out.println("You choose number without action\n");
                }
            }
        }while (chooseAct!=11);
    }

    /**
     * Passes an dbCarServiceImpl bean, that implements CarService interface,
     * to commonActions() method
     * @see DBCarServiceImpl
     * @see CarService
     */
    public void dbMenu() {
        dbPoolService.createDBTable();          //create table
        //dbPoolService.writeDB();                //fill the table
        commonActions(dbCarServiceImpl, null,null);
        //the rwService for json is null because of it is launched inside the method,
        //the carList is null because it is formed as a result of a database query
    }

    /**
     * Reads car list from json file, using an jsonRWServiceImpl bean,
     * and passes carServiceImpl bean to a commonActions() method
     * @see JsonReadWriteServiceImpl
     * @see CarServiceImpl
     */
    public void jsonMenu() {
        ArrayList<Car> garage = TestCar.getGarage();
        System.out.println("Write json file name:");
        garage = jsonRWService.fileReader(scannerService.getSc().nextLine());
        commonActions(carServiceImpl, jsonRWService, garage);
    }

    /**
     * Reads car list from txt file, using txtRWServiceImpl bean,
     * and pass carServiceImpl bean it to a commonActions() method
     * @see TxtReadWriteServiceImpl
     * @see CarServiceImpl
     */
    public void txtMenu() {
        ArrayList<Car> garage = TestCar.getGarage();
        System.out.println("Write txt file name:");
        garage = txtRWService.fileReader(scannerService.getSc().nextLine());
        commonActions(carServiceImpl, txtRWService, garage);
    }
}
