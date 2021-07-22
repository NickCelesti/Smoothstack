package LMS_Final_Assignment;

import java.sql.SQLException;

public class Main {

    /**
     * Acts as the
     * 
     * @throws SQLException
     */
    public static void menuMain() {

        System.out.println("Welcome to the SS Library Management System. Which categroy of a user are you");
        System.out.println();
        System.out.println("1) Librarian");
        System.out.println("2) Administrator");
        System.out.println("3) Borrower");
        System.out.println();

        int type = InputHandler.getIntInput(1, 3);

        if (type == 1) {
            Librarian.menuLib1();
        } else if (type == 2) {

        } else { // type == 3

            int cardNo = InputHandler.getCardInput();
            Borrower.menuBorr1(cardNo);
        }
    }

    public static void main(String[] args) {
        menuMain();
    }
}
