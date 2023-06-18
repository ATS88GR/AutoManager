package packages;

import packages.model.Car;
import packages.service.CarServiceImpl;
import packages.service.DialogMenuService;
import packages.service.ReadWriteServiceImpl;
import packages.utils.TestCar;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        chooseAction();
        ArrayList<Car> garage = TestCar.getGarage();
        CarServiceImpl carService = new CarServiceImpl();

/*        System.out.println("Max cost of car: " + carService.getMaxCostCar(garage).getCost() + "\n");

        System.out.println("Min cost of car: " + carService.getMinCostCar(garage).getCost() + "\n");

        System.out.println("Get brand Volvo from list: ");
        carService.printCarList(carService.findBrandList("Volvo", garage));

        System.out.println("Get model A4 of brand from list: ");
        carService.printCarList(carService.findModelList("A4", garage));

        System.out.println("Get cars by price range from 5000 to 15000: ");
        carService.printCarList(carService.getListByPriceRange(5000, 15000, garage));

        System.out.println("Sort list by price");
        carService.printCarList(carService.sortListByPrice(garage));

        System.out.println("Sort list by brand");
        carService.printCarList(carService.sortListByBrand(garage));

        ReadWriteServiceImpl readWriteService = new ReadWriteServiceImpl();

        readWriteService.FileWriter(garage, "CarInfo");

        readWriteService.FileReader("CarInfo");
        System.out.println();*/

        /*System.out.println("Building JSON array with cars information:");
        System.out.println(readWriteService.buildCarsJson(garage));*/
////////////////////////////////////////////////////////////////////////
      /*  String url = "jdbc:postgresql://localhost:5432/postgres";
        String name = "postgres";
        String password = "password";
        try {
           Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Garage ORDER BY Brand;");
            while (rs.next()) {
                System.out.println("Year: " + rs.getInt("Year") + ", Brand: " + rs.getString("Brand"));
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
    private static void chooseAction() {     //actions in main menu
        int chooseAct=4;   //int for saving choose
        try {
            do {
                System.out.println("""
                        Select the number of service:
                        1. Data base.
                        2. JSON.
                        3. TXT.
                        4. Exit.
                        """);
                if (sc.hasNextLine()) {
                    chooseAct = Integer.parseInt(sc.nextLine());
                    actions(chooseAct);
                }
            }while (chooseAct != 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void actions(int act) {
        switch (act) {
            case 1 -> {
                DialogMenuService.dbMenu();}
            case 2 -> {
                DialogMenuService.jsonMenu();}
            case 3 -> {
                DialogMenuService.txtMenu();}
            case 4 -> {
                System.out.println("Exit the program");}
            default -> System.out.println("You choose number without action");
        }
    }
}