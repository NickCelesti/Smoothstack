package LMS_Final_Assignment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrower {

    public static void menuBorr1(int cardNo) {
        System.out.println("1) Check out a book");
        System.out.println("2) Return a book");
        System.out.println("3) Quit to previous");

        int ans = InputHandler.getIntInput(1, 3);

        if (ans == 1) {
            checkOut(cardNo);
        } else if (ans == 2) {

        } else {
            Main.menuMain();
        }
    }

    private static void checkOut(int cardNo) {
        BorrowerSQL borr = new BorrowerSQL();
        System.out.println("Pick the Branch you want to check out from: ");
        int rowCount = borr.printBranches();

        int branchId = InputHandler.getIntInput(1, rowCount);

        if (branchId == rowCount) {
            menuBorr1(cardNo);
        } else {
            System.out.println("Pick the Book you want to check out");

            int bookId = borr.printBooks(branchId);

            if (bookId == 0) {
                menuBorr1(cardNo);
            } else {
                borr.checkOut(bookId, branchId);
            }
        }
    }
}
