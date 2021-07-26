package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.DAO.AuthorDAO;
import LMS_Final_Assignment.DAO.BookCopyDAO;
import LMS_Final_Assignment.DAO.BookDAO;
import LMS_Final_Assignment.DAO.LibraryBranchDAO;
import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.BookCopy;
import LMS_Final_Assignment.Domain.LibraryBranch;

public class LibrarianService {

    Util util = new Util();

    public List<LibraryBranch> readAllBranches() throws SQLException {
        Connection conn = null;
        List<LibraryBranch> branches = new ArrayList<>();
        try {
            conn = util.getConnection();
            LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
            branches = ldao.readAllBranches();
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

    public String updateBranchName(Integer branchId, String newName) throws SQLException {
        Connection conn = null;
        String returnString;
        try {
            conn = util.getConnection();
            LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
            LibraryBranch branch = ldao.readBranchFromId(branchId);
            branch.setName(newName);
            ldao.update(branch);
            conn.commit();
            returnString = "Successfully updated name";
        } catch (Exception e) {
            conn.rollback();
            returnString = "Name failed to update";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return returnString;
    }

    public String updateBranchAddress(Integer branchId, String newAddress) throws SQLException {
        Connection conn = null;
        String returnString;
        try {
            conn = util.getConnection();
            LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
            LibraryBranch branch = ldao.readBranchFromId(branchId);
            branch.setAddress(newAddress);
            ldao.update(branch);
            conn.commit();
            returnString = "Successfully updated address";
        } catch (Exception e) {
            conn.rollback();
            returnString = "Address failed to update";
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return returnString;
    }

    public List<Book> readAllBooks() throws SQLException {
        Connection conn = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            books = bdao.readAllBooks();
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

    public BookCopy getBookCopy(Integer bookId, Integer branchId) throws SQLException {
        Connection conn = null;
        BookCopy bc = null;
        try {
            conn = util.getConnection();
            BookCopyDAO bcdao = new BookCopyDAO(conn);
            bc = bcdao.readBookCopyById(bookId, branchId);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        if (bc == null) {
            try {
                conn = util.getConnection();
                BookCopyDAO bcdao = new BookCopyDAO(conn);
                bc = new BookCopy(0, bookId, branchId);
                bcdao.add(bc);
                System.out.println("Created data row");
            } catch (Exception e) {
                System.out.println("Failed to create data");
                conn.rollback();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }

        return bc;
    }

    public void updateCopies(BookCopy bCopy, Integer newNoOfCopies) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            BookCopyDAO bcd = new BookCopyDAO(conn);
            bCopy.setNoOfCopies(newNoOfCopies);
            bcd.update(bCopy);

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
