package LMS_Final_Assignment.Menu;

import java.sql.SQLException;

import LMS_Final_Assignment.InputHandler;

public class MainMenu {

    /**
     * Acts as the
     * 
     * @throws SQLException
     */
    public static void menuMain() throws SQLException {

        System.out.println("Welcome to the SS Library Management System. Which categroy of a user are you");
        System.out.println();
        System.out.println("1) Librarian");
        System.out.println("2) Administrator");
        System.out.println("3) Borrower");
        System.out.println();

        int type = InputHandler.getIntInput(1, 3);

        if (type == 1) {
            LibrarianMenu.menuLib1();
        } else if (type == 2) {
            AdminMenu.menuAdmin1();
        } else { // type == 3
            BorrowerMenu.menuBorr0();
        }
    }

    public static void main(String[] args) throws SQLException {
        menuMain();
    }
}
