package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.BookCopy;

public class BookCopyDAO extends BaseDAO<BookCopy> {

    public BookCopyDAO(Connection conn) {
        super(conn);
    }

    public void add(BookCopy bookCopy) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO tbl_book_copies VALUES (?, ?, ?)",
                new Object[] { bookCopy.getBookId(), bookCopy.getBranchId(), bookCopy.getNoOfCopies() });
    }

    public void update(BookCopy bookCopy) throws SQLException, ClassNotFoundException {
        this.save("UPDATE tbl_book_copies SET noOfCopies = ? where bookId = ? AND branchId = ?",
                new Object[] { bookCopy.getNoOfCopies(), bookCopy.getBookId(), bookCopy.getBranchId() });
    }

    public void delete(BookCopy bookCopy) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",
                new Object[] { bookCopy.getBookId(), bookCopy.getBranchId() });
    }

    public List<BookCopy> readAllBookCopies() throws SQLException, ClassNotFoundException {
        return read("select * from tbl_book_copies", null);
    }

    public BookCopy readBookCopyById(Integer bookId, Integer branchId) throws SQLException, ClassNotFoundException {
        return readSingle("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",
                new Object[] { bookId, branchId });
    }

    // @Override
    // public List<BookCopy> extract(ResultSet resultSet) {
    // return null;
    // }

    @Override
    public List<BookCopy> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookCopy> copies = new ArrayList<>();
        while (rs.next()) {
            BookCopy copy = new BookCopy();
            copy.setBookId(rs.getInt("bookId"));
            copy.setBranchId(rs.getInt("branchId"));
            copy.setNoOfCopies(rs.getInt("noOfCopies"));
        }
        return copies;
    }

    @Override
    public BookCopy extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
        if (rs.next()) {
            BookCopy copy = new BookCopy();
            copy.setBookId(rs.getInt("bookId"));
            copy.setBranchId(rs.getInt("branchId"));
            copy.setNoOfCopies(rs.getInt("noOfCopies"));
            return copy;
        }
        return null;
    }

}