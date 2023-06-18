package packages.service;

import packages.Main;
import packages.model.Car;
import packages.utils.TestCar;

import java.util.ArrayList;

public class DialogMenuService {

    private static void commonActions(CarService carService, ReadWriteService rwService, ArrayList <Car> carList){
        int chooseAct = 10;
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
                    8. Write cars info to file.
                    9. Show cars info from file/database.
                    10. Exit.
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
                        System.out.println("Write file name to save:");
                        if (Main.sc.hasNextLine()) {
                            if(carList == null) new ReadWriteServiceImpl().fileWriter(carService.sortListByPrice(null), Main.sc.nextLine());
                            else rwService.fileWriter(carList, Main.sc.nextLine());
                        }
                    }
                    case 9 -> {
                        if(carList == null) carService.sortListByPrice(null);
                        else {
                            System.out.println("Write file name to read:");
                            rwService.fileReader(Main.sc.nextLine());
                        }
                    }
                    case 10 -> System.out.println("Exit from menu\n");
                    default -> System.out.println("You choose number without action\n");
                }
            }
        }while (chooseAct!=10);
    }

    public static void dbMenu() {               // menu fo working with database
        DBCarServiceImpl dbService = new DBCarServiceImpl();
        dbService.setConnection();
        //dbService.createDB();
        //dbService.writeDB();
        commonActions(dbService, null,null);
        /*int chooseAct = 10;
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
                    8. Write cars info from database to txt file.
                    9. Show cars info from database.
                    10. Exit.
                    """);
            if (Main.sc.hasNextLine()) {
                chooseAct = Integer.parseInt(Main.sc.nextLine());
                switch (chooseAct) {
                    case 1 -> dbService.getMaxCostCar(null);
                    case 2 -> dbService.getMinCostCar(null);
                    case 3 -> {
                        System.out.println("Write the brand of car to search:");
                        if(Main.sc.hasNextLine())
                            dbService.findBrandList(Main.sc.nextLine(), null);
                    }
                    case 4 -> {
                        System.out.println("Write the model of car to search:");
                        if(Main.sc.hasNextLine())
                            dbService.findModelList(Main.sc.nextLine(), null);
                    }
                    case 5 -> {
                        System.out.println("Write min price:");
                        int minPrice = Integer.parseInt(Main.sc.nextLine());
                        System.out.println("Write max price:");
                        int maxPrice = Integer.parseInt(Main.sc.nextLine());
                        dbService.getListByPriceRange(minPrice, maxPrice, null);
                    }
                    case 6 -> dbService.sortListByPrice(null);
                    case 7 -> dbService.sortListByBrand(null);
                    case 8 -> {
                        System.out.println("Write file name to save:");
                        if (Main.sc.hasNextLine())
                            new ReadWriteServiceImpl().fileWriter(dbService.getAllBase("n"), Main.sc.nextLine());
                    }
                    case 9 -> dbService.getAllBase("y");
                    case 10 -> System.out.println("Exit database menu\n");
                    default -> System.out.println("You choose number without action\n");
                }
            }
        }while (chooseAct!=10);*/
        dbService.closeDB();
    }

    public static void jsonMenu() {
        ArrayList<Car> garage = TestCar.getGarage();
        /*JsonReadWriteServiceImpl jsonRWService = new JsonReadWriteServiceImpl();
        System.out.println("Write json file name:");
        garage = jsonRWService.fileReader(Main.sc.nextLine());*/
        CarServiceImpl carService = new CarServiceImpl();
        commonActions(carService, new JsonReadWriteServiceImpl(), garage);
        /*int chooseAct = 10;
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
                    8. Write cars info to json file.
                    9. Show cars info from json file.
                    10. Exit.
                    """);
            if (Main.sc.hasNextLine()) {
                chooseAct = Integer.parseInt(Main.sc.nextLine());
                switch (chooseAct) {
                    case 1 -> carService.getMaxCostCar(garage);
                    case 2 -> carService.getMinCostCar(garage);
                    case 3 -> {
                        System.out.println("Write the brand of car to search:");
                        if(Main.sc.hasNextLine())
                            carService.findBrandList(Main.sc.nextLine(), garage);
                    }
                    case 4 -> {
                        System.out.println("Write the model of car to search:");
                        if(Main.sc.hasNextLine())
                            carService.findModelList(Main.sc.nextLine(), garage);
                    }
                    case 5 -> {
                        System.out.println("Write min price:");
                        int minPrice = Integer.parseInt(Main.sc.nextLine());
                        System.out.println("Write max price:");
                        int maxPrice = Integer.parseInt(Main.sc.nextLine());
                        carService.getListByPriceRange(minPrice, maxPrice, garage);
                    }
                    case 6 -> carService.sortListByPrice(garage);
                    case 7 -> carService.sortListByBrand(garage);
                    case 8 -> {
                        System.out.println("Write file name to save:");
                        if (Main.sc.hasNextLine())
                            new JsonReadWriteServiceImpl().fileWriter(garage, Main.sc.nextLine());
                    }
                    case 9 -> {
                        System.out.println("Write file name to read:");
                        new JsonReadWriteServiceImpl().fileReader(Main.sc.nextLine());
                    }
                    case 10 -> System.out.println("Exit database menu\n");
                    default -> System.out.println("You choose number without action\n");
                }
            }
        }while (chooseAct!=10);*/
    }

    public static void txtMenu() {
        ArrayList<Car> garage = TestCar.getGarage();
        CarServiceImpl carService = new CarServiceImpl();
        commonActions(carService, new ReadWriteServiceImpl(), garage);
        /*int chooseAct = 10;
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
                    9. Show cars info from txt file.
                    10. Exit.
                    """);
            if (Main.sc.hasNextLine()) {
                chooseAct = Integer.parseInt(Main.sc.nextLine());
                switch (chooseAct) {
                    case 1 -> carService.getMaxCostCar(garage);
                    case 2 -> carService.getMinCostCar(garage);
                    case 3 -> {
                        System.out.println("Write the brand of car to search:");
                        if(Main.sc.hasNextLine())
                            carService.findBrandList(Main.sc.nextLine(), garage);
                    }
                    case 4 -> {
                        System.out.println("Write the model of car to search:");
                        if(Main.sc.hasNextLine())
                            carService.findModelList(Main.sc.nextLine(), garage);
                    }
                    case 5 -> {
                        System.out.println("Write min price:");
                        int minPrice = Integer.parseInt(Main.sc.nextLine());
                        System.out.println("Write max price:");
                        int maxPrice = Integer.parseInt(Main.sc.nextLine());
                        carService.getListByPriceRange(minPrice, maxPrice, garage);
                    }
                    case 6 -> carService.sortListByPrice(garage);
                    case 7 -> carService.sortListByBrand(garage);
                    case 8 -> {
                        System.out.println("Write file name to save:");
                        if (Main.sc.hasNextLine())
                            new ReadWriteServiceImpl().fileWriter(garage, Main.sc.nextLine());
                    }
                    case 9 -> {
                        System.out.println("Write file name to read:");
                        new ReadWriteServiceImpl().fileReader(Main.sc.nextLine());
                    }
                    case 10 -> System.out.println("Exit database menu\n");
                    default -> System.out.println("You choose number without action\n");
                }
            }
        }while (chooseAct!=10);*/
    }
}
