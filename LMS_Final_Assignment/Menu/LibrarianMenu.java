package LMS_Final_Assignment.Menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.InputHandler;
import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.BookCopy;
import LMS_Final_Assignment.Domain.LibraryBranch;
import LMS_Final_Assignment.Services.LibrarianService;

public class LibrarianMenu {

    private static LibrarianService service = new LibrarianService();

    public static void menuLib1() throws SQLException {
        System.out.println("1) Enter Branch you manage");
        System.out.println("2) Quit to previous");
        System.out.println();

        int ans = InputHandler.getIntInput(1, 2);

        if (ans == 1) {
            menuLib2();
        } else {
            MainMenu.menuMain();
        }
    }

    private static void menuLib2() throws SQLException { // TODO: fix try catch here
        List<LibraryBranch> branches = new ArrayList<>();

        branches = service.readAllBranches();

        int index = 1;
        for (LibraryBranch b : branches) {
            System.out.println(index + ") " + b.getName() + ", " + b.getAddress());
            index++;
        }

        System.out.println(index + ") Quit to previous");

        int row = InputHandler.getIntInput(1, index);

        if (row == index) {
            menuLib1();
        } else {
            menuLib3(branches.get(row - 1));
        }
    }

    private static void menuLib3(LibraryBranch branch) throws SQLException {
        System.out.println("1) Update the details of the library");
        System.out.println("2) Add copies of Book to the Branch");
        System.out.println("3) Quit to previous");

        int ans = InputHandler.getIntInput(1, 3);

        if (ans == 1) {
            updateBranch(branch);
        } else if (ans == 2) {
            updateBooks(branch);
        } else {
            menuLib2();
        }
    }

    private static void updateBranch(LibraryBranch branch) throws SQLException {

        System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getId()
                + " and Branch Name: " + branch.getName() + ". \nEnter ‘quit’ at any prompt to cancel operation.");

        System.out.println();
        System.out.println("Please enter new branch name or enter N/A for no change:");
        String newName = InputHandler.getStringInput();

        if (newName.compareToIgnoreCase("quit") == 0 || newName.compareToIgnoreCase("quit") == 0) {
            System.out.println("Operation Cancelled");
            menuLib3(branch);
            return;
        }

        System.out.println("Please enter new branch address or enter N/A for no change:");
        String newAddress = InputHandler.getStringInput();

        if (newAddress.compareToIgnoreCase("quit") == 0) {
            System.out.println("Operation Cancelled");
            menuLib3(branch);
            return;
        }

        if (newName.compareToIgnoreCase("N/A") != 0) {
            service.updateBranchName(branch.getId(), newName);
        }
        if (newAddress.compareToIgnoreCase("N/A") != 0) {
            service.updateBranchAddress(branch.getId(), newAddress);
        }

        System.out.println("Successfully updated branch");
        menuLib3(branch);
        return;
    }

    /**
     * 
     * @param branch
     */
    private static void updateBooks(LibraryBranch branch) throws SQLException {
        List<Book> books = new ArrayList<>();
        BookCopy bCopy = new BookCopy();
        // aDao.readAuthorsById(authorId)
        books = service.readAllBooks();
        System.out.println("Pick the book you want to add copies of to your branch");
        int index = 1;
        for (Book b : books) {
            Author author = service.readAuthorFromId(b.getAuthorId());
            System.out.println(index + ") " + b.getTitle() + " by " + author.getName());
            index++;
        }
        System.out.println(index + ") Quit to previous\n");

        int row = InputHandler.getIntInput(1, index);

        if (row == index) {
            menuLib3(branch);
            return;
        }

        bCopy = service.getBookCopy(books.get(row - 1).getId(), branch.getId());

        System.out.println("Existing number of copies: " + bCopy.getNoOfCopies());

        System.out.println("Enter new number of copies: \n");
        int newBookCount = InputHandler.getIntInput(0, 10000); // arbitrarily large number

        service.updateCopies(bCopy, newBookCount);
        menuLib3(branch);
        return;
    }
}
