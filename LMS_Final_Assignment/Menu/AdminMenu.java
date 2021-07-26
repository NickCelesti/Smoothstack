package LMS_Final_Assignment.Menu;

import java.sql.SQLException;

import LMS_Final_Assignment.Services.AdminServiceAuthor;
import LMS_Final_Assignment.Services.AdminServiceBook;
import LMS_Final_Assignment.Services.AdminServiceBookLoan;
import LMS_Final_Assignment.Services.AdminServiceBorrower;
import LMS_Final_Assignment.Services.AdminServiceLibraryBranch;
import LMS_Final_Assignment.Services.AdminServicePublisher;
import LMS_Final_Assignment.Services.InputHandler;

public class AdminMenu {

    public static void menuAdmin1() throws SQLException {

        System.out.println("1) Add/Update/Delete/Read Books");
        System.out.println("2) Add/Update/Delete/Read Authors");
        System.out.println("3) Add/Update/Delete/Read Publishers");
        System.out.println("4) Add/Update/Delete/Read Library Branches");
        System.out.println("5) Add/Update/Delete/Read Borrowers");
        System.out.println("6) Over-ride Due Date for a Book Loan");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 6);

        if (ans == 1) {
            crudBooks();
        } else if (ans == 2) {
            crudAuthors();
        } else if (ans == 3) {
            crudPublishers();
        } else if (ans == 4) {
            crudBranches();
        } else if (ans == 5) {
            crudBorrowers();
        } else if (ans == 6) {
            bookLoan();
        }
        MainMenu.menuMain();
    }

    private static void crudBooks() throws SQLException {
        AdminServiceBook bookService = new AdminServiceBook();
        System.out.println("1) Add Book");
        System.out.println("2) Update Book");
        System.out.println("3) Delete Book");
        System.out.println("4) Read all Books");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            System.out.println(bookService.addBook());
        } else if (ans == 2) {
            System.out.println(bookService.updateBook());
        } else if (ans == 3) {
            System.out.println(bookService.deleteBook());
        } else if (ans == 4) {
            System.out.println(bookService.readAllBooks());
        }
    }

    private static void crudAuthors() throws SQLException {
        AdminServiceAuthor authorService = new AdminServiceAuthor();
        System.out.println("1) Add Author");
        System.out.println("2) Update Author");
        System.out.println("3) Delete Author");
        System.out.println("4) Read all Authors");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            System.out.println(authorService.addAuthor());
        } else if (ans == 2) {
            System.out.println(authorService.updateAuthor());
        } else if (ans == 3) {
            System.out.println(authorService.deleteAuthor());
        } else if (ans == 4) {
            System.out.println(authorService.readAllAuthors());
        }
    }

    private static void crudPublishers() throws SQLException {
        AdminServicePublisher publisherService = new AdminServicePublisher();
        System.out.println("1) Add Publisher");
        System.out.println("2) Update Publisher");
        System.out.println("3) Delete Publisher");
        System.out.println("4) Read all Publishers");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            System.out.println(publisherService.addPublisher());
        } else if (ans == 2) {
            System.out.println(publisherService.updatePublisher());
        } else if (ans == 3) {
            System.out.println(publisherService.deletePublisher());
        } else if (ans == 4) {
            System.out.println(publisherService.readAllPublishers());
        }
    }

    private static void crudBranches() throws SQLException {
        AdminServiceLibraryBranch branchService = new AdminServiceLibraryBranch();
        System.out.println("1) Add Branch");
        System.out.println("2) Update Branch");
        System.out.println("3) Delete Branch");
        System.out.println("4) Read all Branches");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            System.out.println(branchService.addBranch());
        } else if (ans == 2) {
            System.out.println(branchService.updateBranch());
        } else if (ans == 3) {
            System.out.println(branchService.deleteBranch());
        } else if (ans == 4) {
            System.out.println(branchService.readAllBranches());
        }
    }

    private static void crudBorrowers() throws SQLException {
        AdminServiceBorrower borrowerService = new AdminServiceBorrower();
        System.out.println("1) Add Borrower");
        System.out.println("2) Update Borrower");
        System.out.println("3) Delete Borrower");
        System.out.println("4) Read all Borrowers");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            System.out.println(borrowerService.addBorrower());
        } else if (ans == 2) {
            System.out.println(borrowerService.updateBorrower());
        } else if (ans == 3) {
            System.out.println(borrowerService.deleteBorrower());
        } else if (ans == 4) {
            System.out.println(borrowerService.readAllBorrowers());
        }
    }

    private static void bookLoan() throws SQLException {
        AdminServiceBookLoan bookLoanService = new AdminServiceBookLoan();

        System.out.println(bookLoanService.overrideBookLoan());
    }

}
