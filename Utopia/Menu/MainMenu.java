package Utopia.Menu;

import java.sql.SQLException;

import Utopia.Services.InputHandler;

public class MainMenu {

    /**
     * Acts as the
     * 
     * @throws SQLException
     */
    public static void menuMain() throws SQLException {

        System.out.println("Welcome to the Utopia Airlines Management System. Which category of a user are you");
        System.out.println();
        System.out.println("1) Employee/Agent");
        System.out.println("2) Administrator");
        System.out.println("3) Traveler");
        System.out.println();

        int type = InputHandler.getIntInput(1, 3);

        if (type == 1) {
            EmployeeMenu.menuEmp1();
        } else if (type == 2) {
            AdminMenu.menuAdmin1();
        } else { // type == 3
            TravelerMenu.menuTrav0();
        }
    }

    public static void main(String[] args) throws SQLException {
        menuMain();
    }
}
