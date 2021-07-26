package LMS_Final_Assignment.Menu;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import LMS_Final_Assignment.InputHandler;
import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.BookLoan;
import LMS_Final_Assignment.Domain.Borrower;
import LMS_Final_Assignment.Domain.LibraryBranch;
import LMS_Final_Assignment.Services.BorrowerService;

public class BorrowerMenu {

    private static BorrowerService service = new BorrowerService();

    public static void menuBorr0() throws SQLException {

        List<Borrower> borrList = service.readAllBorrowers();

        List<Integer> cardNos = borrList.stream().map(n -> (n.getCardNo())).collect(Collectors.toList());

        System.out.println("Enter your card number: ");
        int id = 0;
        while (true) {
            id = InputHandler.getCardInput();
            if (cardNos.contains(id)) {
                System.out.println("Accepted");
                break;
            } else {
                System.out.println("Card number not in system, try again");
            }
        }
        menuBorr1(id);
    }

    private static void menuBorr1(int cardNo) throws SQLException {

        System.out.println("1) Check out a book");
        System.out.println("2) Return a book");
        System.out.println("3) Quit to previous");

        int ans = InputHandler.getIntInput(1, 3);

        if (ans == 1) {
            checkOutBook(cardNo);
        } else if (ans == 2) {
            returnBook(cardNo);
        } else {
            MainMenu.menuMain();
        }
    }

    private static void checkOutBook(int cardNo) throws SQLException {
        List<LibraryBranch> branches = service.readAllBranches();

        System.out.println("Pick the Branch you want to check out from: ");
        int branchIndex = 1;
        for (LibraryBranch b : branches) {
            System.out.println(branchIndex + ") " + b.getName() + ", " + b.getAddress());
            branchIndex++;
        }
        System.out.println(branchIndex + ") Quit to previous");
        int branchRow = InputHandler.getIntInput(1, branchIndex);

        if (branchRow == branchIndex) {
            System.out.println("Transaction Cancelled");
        } else {
            System.out.println("Pick the Book you want to check out");

            List<Book> books = service.readAllBooksWithCopies(branches.get(branchRow - 1).getId());
            int bookIndex = 1;
            for (Book b : books) {
                Author author = service.readAuthorFromId(b.getAuthorId());
                System.out.println(bookIndex + ") " + b.getTitle() + " by " + author.getName());
                bookIndex++;
            }
            System.out.println(bookIndex + ") Quit to previous");
            int bookRow = InputHandler.getIntInput(1, bookIndex);

            if (bookRow == bookIndex) {
                System.out.println("Transation Cancelled");
            } else {
                service.checkOut(books.get(bookRow - 1).getId(), branches.get(branchRow - 1).getId(), cardNo);
            }
        }
        menuBorr1(cardNo);
    }

    private static void returnBook(int cardNo) throws SQLException {

        List<LibraryBranch> branches = service.readAllBranches();

        System.out.println("Pick the Branch you want to check out from: ");
        int branchIndex = 1;
        for (LibraryBranch b : branches) {
            System.out.println(branchIndex + ") " + b.getName() + ", " + b.getAddress());
            branchIndex++;
        }
        System.out.println(branchIndex + ") Quit to previous");
        int branchRow = InputHandler.getIntInput(1, branchIndex);

        if (branchRow == branchIndex) {
            System.out.println("Transaction Cancelled");
        } else {
            System.out.println("Pick the Book you want to return");

            List<BookLoan> bookLoans = service.readLoans(branches.get(branchRow - 1).getId(), cardNo);
            int bookIndex = 1;

            for (BookLoan bl : bookLoans) {
                Book book = service.readBookFromId(bl.getBookId());
                Author author = service.readAuthorFromId(book.getAuthorId());

                System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getName());
                bookIndex++;
            }
            System.out.println(bookIndex + ") Quit to previous");
            int bookRow = InputHandler.getIntInput(1, bookIndex);

            if (bookRow == bookIndex) {
                System.out.println("Transation Cancelled");
            } else {
                // TODO: check for a duplicate entry
                service.returnBook(bookLoans.get(bookRow - 1).getBookId(), branches.get(branchRow - 1).getId(), cardNo);
            }
        }
        menuBorr1(cardNo);
    }
}
