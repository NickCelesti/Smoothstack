package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan> {

    public BookLoanDAO(Connection conn) {
        super(conn);
    }

    public void add(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_book_loans VALUES (?, ?, ?, ?, ?)", new Object[] { bookLoan.getBookId(),
                bookLoan.getBranchId(), bookLoan.getCardNo(), bookLoan.getDateOut(), bookLoan.getDueDate() });
    }

    public void update(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_book_loans SET dateOut = ?, dueDate = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?",
                new Object[] { bookLoan.getDateOut(), bookLoan.getDueDate(), bookLoan.getBookId(),
                        bookLoan.getBranchId(), bookLoan.getCardNo() });
    }

    public void delete(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
        save("delete from tbl_book_loans where bookId = ? AND branchId = ? AND cardNo = ?",
                new Object[] { bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo() });
    }

    public List<BookLoan> readAllLoans() throws SQLException, ClassNotFoundException {
        return this.read("select * from tbl_book_loans ORDER BY dueDate asc", null);
    }

    public List<BookLoan> readLoansByCardNoAndBranchId(Integer cardNo, Integer branchId)
            throws SQLException, ClassNotFoundException {
        return this.read("select * from tbl_book_loans where cardNo = ? AND branchId = ?",
                new Object[] { cardNo, branchId });
    }

    public List<BookLoan> readLoansByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
        return this.read("select * from tbl_book_loans where cardNo = ? ORDER BY dueDate asc", new Object[] { cardNo });
    }

    @Override
    public List<BookLoan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<BookLoan> blList = new ArrayList<>();
        while (rs.next()) {
            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(rs.getInt("bookId"));
            bookLoan.setBranchId(rs.getInt("branchId"));
            bookLoan.setCardNo(rs.getInt("cardNo"));
            bookLoan.setDateOut(rs.getTimestamp("dateOut"));
            bookLoan.setDueDate(rs.getTimestamp("dueDate"));
            blList.add(bookLoan);
        }

        return blList;
    }

    @Override
    public BookLoan extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(rs.getInt("bookId"));
            bookLoan.setBranchId(rs.getInt("branchId"));
            bookLoan.setCardNo(rs.getInt("cardNo"));
            bookLoan.setDateOut(rs.getTimestamp("dateOut"));
            bookLoan.setDueDate(rs.getTimestamp("dueDate"));
            return bookLoan;
        }

        return null;
    }
}
