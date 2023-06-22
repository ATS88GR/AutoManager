package packages;

import packages.service.DBPoolService;
import packages.service.DialogMenuService;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static DBPoolService source;
    public static void main(String[] args) {
        source = new DBPoolService();
        source.setConnection();
        chooseAction();         //actions in main menu
        source.closeConnection();
    }
    private static void chooseAction() {
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
        DialogMenuService dialogMenuService = new DialogMenuService();
        switch (act) {
            case 1 -> dialogMenuService.dbMenu();   //dialog menu database
            case 2 -> dialogMenuService.jsonMenu(); //dialog menu json
            case 3 -> dialogMenuService.txtMenu();  //dialog menu txt
            case 4 -> System.out.println("Exit the program");
            default -> System.out.println("You choose number without action");
        }
    }
}