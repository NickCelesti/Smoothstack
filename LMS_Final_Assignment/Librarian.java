package LMS_Final_Assignment;

public class Librarian {
    // TODO: add documentation
    public static void menuLib1() {

        System.out.println("1) Enter Branch you manage");
        System.out.println("2) Quit to previous");
        System.out.println();

        int ans = InputHandler.getIntInput(1, 2);

        if (ans == 1) {
            menuLib2();
        } else {
            Main.menuMain();
        }
    }

    private static void menuLib2() { // ugly method, fix later
        LibrarianSQL lib = new LibrarianSQL();

        int rowCount = lib.printBranches();

        int ans = InputHandler.getIntInput(1, rowCount);

        if (ans == rowCount) {
            menuLib1();
        } else {
            menuLib3(ans);
        }
    }

    private static void menuLib3(int branchId) {
        System.out.println("1) Update the details of the library");
        System.out.println("2) Add copies of Book to the Branch");
        System.out.println("3) Quit to previous");

        int ans = InputHandler.getIntInput(1, 3);

        if (ans == 1) {
            updateBranch(branchId);
        } else if (ans == 2) {
            updateBooks(branchId);
        } else {
            menuLib2();
        }
    }

    private static void updateBranch(int branchId) {
        LibrarianSQL lib = new LibrarianSQL();
        String branchName = lib.getBranchName(branchId);
        System.out.println("You have chosen to update the Branch with Branch Id: " + branchId + " and Branch Name: "
                + branchName + ". Enter ‘quit’ at any prompt to cancel operation.");

        System.out.println();
        System.out.println("Please enter new branch name or enter N/A for no change:");
        String newName = InputHandler.getStringInput();

        if (newName.compareToIgnoreCase("quit") == 0 || newName.compareToIgnoreCase("quit") == 0) {
            System.out.println("Operation Cancelled");
            menuLib3(branchId);
            return;
        }

        System.out.println("Please enter new branch address or enter N/A for no change:");
        String newAddress = InputHandler.getStringInput();

        if (newAddress.compareToIgnoreCase("quit") == 0) {
            System.out.println("Operation Cancelled");
            menuLib3(branchId);
            return;
        }

        if (newName.compareToIgnoreCase("N/A") != 0) {
            lib.updateBranchName(newName, branchId);
        }
        if (newAddress.compareToIgnoreCase("N/A") != 0) {
            lib.updateBranchAddress(newAddress, branchId);
        }

        System.out.println("Successfully updated branch");
        menuLib3(branchId);
        return;
    }

    /**
     * 
     * @param branchId
     */
    private static void updateBooks(int branchId) {
        LibrarianSQL lib = new LibrarianSQL();
        int rowCount = lib.printBooks();
        int ans = InputHandler.getIntInput(1, rowCount);

        if (ans != rowCount) {
            int existingBookCount = lib.getBookCopies(ans, branchId);
            if (existingBookCount == -1) { // will be -1 if the row does not exist
                System.out.println("Existing number of copies: 0");
            } else {
                System.out.println("Existing number of copies: " + existingBookCount);
            }
            System.out.println("Enter new number of copies: \n");
            int newBookCount = InputHandler.getIntInput(0, 10000); // arbitrarily large number
            lib.updateBookCopies(ans, branchId, existingBookCount, newBookCount);
        }
        menuLib3(branchId);
        return;
    }
}
