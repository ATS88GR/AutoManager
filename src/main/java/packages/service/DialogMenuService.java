package packages.service;

import packages.Main;
import packages.model.Car;
import packages.utils.TestCar;

import java.util.ArrayList;

public class DialogMenuService {

    //common actions for database, json, txt
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
            if (Main.sc.hasNextLine()) {
                chooseAct = Integer.parseInt(Main.sc.nextLine());
                switch (chooseAct) {
                    case 1 -> carService.getMaxCostCar(carList);
                    case 2 -> carService.getMinCostCar(carList);
                    case 3 -> {
                        System.out.println("Write the brand of car to search:");
                        if(Main.sc.hasNextLine())
                            carService.findBrandList(Main.sc.nextLine(), carList);
                    }
                    case 4 -> {
                        System.out.println("Write the model of car to search:");
                        if(Main.sc.hasNextLine())
                            carService.findModelList(Main.sc.nextLine(), carList);
                    }
                    case 5 -> {
                        System.out.println("Write min price:");
                        int minPrice = Integer.parseInt(Main.sc.nextLine());
                        System.out.println("Write max price:");
                        int maxPrice = Integer.parseInt(Main.sc.nextLine());
                        carService.getListByPriceRange(minPrice, maxPrice, carList);
                    }
                    case 6 -> carService.sortListByPrice(carList);
                    case 7 -> carService.sortListByBrand(carList);
                    case 8 -> {
                        System.out.println("Write txt file name to save:");
                        if (Main.sc.hasNextLine()) {
                            if(carList == null) new TxtReadWriteServiceImpl().fileWriter(carService.sortListByPrice(null), Main.sc.nextLine());
                            else new TxtReadWriteServiceImpl().fileWriter(carList, Main.sc.nextLine());
                        }
                    }
                    case 9 -> {
                        System.out.println("Write json file name to save:");
                        if (Main.sc.hasNextLine()) {
                            if(carList == null) new JsonReadWriteServiceImpl().fileWriter(carService.sortListByPrice(null), Main.sc.nextLine());
                            else new JsonReadWriteServiceImpl().fileWriter(carList, Main.sc.nextLine());
                        }
                    }
                    case 10 -> {
                        if(carList == null) carService.sortListByPrice(null);
                        else {
                            System.out.println("Write file name to read:");
                            rwService.fileReader(Main.sc.nextLine());
                        }
                    }
                    case 11 -> System.out.println("Exit from menu\n");
                    default -> System.out.println("You choose number without action\n");
                }
            }
        }while (chooseAct!=11);
    }

    public void dbMenu() {               // menu fo working with database
        DBCarServiceImpl dbService = new DBCarServiceImpl();
        dbService.setConnection();
        /*dbService.createDB();
        dbService.writeDB();*/
        commonActions(dbService, null,null);
        dbService.closeDB();
    }

    public void jsonMenu() {            // menu fo working with json
        ArrayList<Car> garage = TestCar.getGarage();
        JsonReadWriteServiceImpl jsonRWService = new JsonReadWriteServiceImpl();
        System.out.println("Write json file name:");
        garage = jsonRWService.fileReader(Main.sc.nextLine());
        CarServiceImpl carService = new CarServiceImpl();
        commonActions(carService, new JsonReadWriteServiceImpl(), garage);
    }

    public void txtMenu() {             // menu fo working with txt files
        ArrayList<Car> garage = TestCar.getGarage();
        TxtReadWriteServiceImpl rwService = new TxtReadWriteServiceImpl();
        System.out.println("Write txt file name:");
        garage = rwService.fileReader(Main.sc.nextLine());
        CarServiceImpl carService = new CarServiceImpl();
        commonActions(carService, new TxtReadWriteServiceImpl(), garage);
    }
}
