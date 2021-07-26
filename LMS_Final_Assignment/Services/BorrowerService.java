package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.DAO.AuthorDAO;
import LMS_Final_Assignment.DAO.BookCopyDAO;
import LMS_Final_Assignment.DAO.BookDAO;
import LMS_Final_Assignment.DAO.BookLoanDAO;
import LMS_Final_Assignment.DAO.BorrowerDAO;
import LMS_Final_Assignment.DAO.LibraryBranchDAO;
import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.BookCopy;
import LMS_Final_Assignment.Domain.BookLoan;
import LMS_Final_Assignment.Domain.Borrower;
import LMS_Final_Assignment.Domain.LibraryBranch;

public class BorrowerService {

    Util util = new Util();

    public List<Borrower> readAllBorrowers() throws SQLException {
        Connection conn = null;
        List<Borrower> borrowers = new ArrayList<>();
        try {
            conn = util.getConnection();
            BorrowerDAO bdao = new BorrowerDAO(conn);
            borrowers = bdao.readAllBorrowers();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return borrowers;
    }

    public List<LibraryBranch> readAllBranches() throws SQLException {
        Connection conn = null;
        List<LibraryBranch> branches = new ArrayList<>();
        try {
            conn = util.getConnection();
            LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
            branches = lbdao.readAllBranches();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return branches;
    }

    public List<Book> readAllBooksWithCopies(Integer branchId) throws SQLException {
        Connection conn = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            books = bdao.readBooksWithCopy(branchId);
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return books;
    }

    public Author readAuthorFromId(Integer authorId) throws SQLException {
        Connection conn = null;
        Author author = new Author();
        try {
            conn = util.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);
            author = adao.readAuthorById(authorId);
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return author;
    }

    public void checkOut(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            BookLoanDAO bookLoanDao = new BookLoanDAO(conn);
            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(bookId);
            bookLoan.setBranchId(branchId);
            bookLoan.setCardNo(cardNo);
            bookLoan.setDateOut(Timestamp.valueOf(LocalDateTime.now()));
            bookLoan.setDueDate(Timestamp.valueOf(LocalDateTime.now().plusWeeks(1)));
            bookLoanDao.add(bookLoan);

            BookCopyDAO bookCopyDao = new BookCopyDAO(conn);
            BookCopy bookCopy = bookCopyDao.readBookCopyById(bookId, branchId);
            bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() - 1);
            bookCopyDao.update(bookCopy);
            conn.commit();
            System.out.println("Successfully checked out book");

        } catch (Exception e) {
            conn.rollback();
            System.out.println("Failed to check out book");

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<BookLoan> readLoans(Integer branchId, Integer cardNo) throws SQLException {
        Connection conn = null;
        List<BookLoan> bookLoans = new ArrayList<>();
        try {
            conn = util.getConnection();
            BookLoanDAO bldao = new BookLoanDAO(conn);
            bookLoans = bldao.readLoansByCardNoAndBranchId(cardNo, branchId);
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bookLoans;
    }

    public Book readBookFromId(Integer bookId) throws SQLException {
        Connection conn = null;
        Book book = new Book();
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            book = bdao.readBookById(bookId);
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return book;
    }

    public void returnBook(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            BookCopyDAO bcdao = new BookCopyDAO(conn);
            BookCopy bookCopy = bcdao.readBookCopyById(bookId, branchId);
            bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() + 1);
            bcdao.update(bookCopy);

            BookLoanDAO bldao = new BookLoanDAO(conn);
            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(bookId);
            bookLoan.setBranchId(branchId);
            bookLoan.setCardNo(cardNo);
            bldao.delete(bookLoan);
            conn.commit();
            System.out.println("Successfully returned book");

        } catch (Exception e) {
            System.out.println("Failed to return book");
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
