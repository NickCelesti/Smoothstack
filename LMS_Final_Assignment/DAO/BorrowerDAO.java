package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {

    public BorrowerDAO(Connection conn) {
        super(conn);
    }

    public void add(Borrower borrower) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO tbl_borrower VALUES (?, ?, ?, ?)",
                new Object[] { borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone() });
    }

    public void addToEnd(Borrower borrower) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(cardNo) FROM `tbl_borrower`)", null);
        save("insert into tbl_borrower values (@max_id + 1, ?, ?, ?)",
                new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone() });
    }

    public void update(Borrower borrower) throws SQLException, ClassNotFoundException {
        this.save("UPDATE tbl_borrower SET name = ?, address = ?, phone = ? WHERE cardNo = ?",
                new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });
    }

    public void delete(Borrower borrower) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[] { borrower.getCardNo() });
    }

    public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
        return read("select * from tbl_borrower", null);
    }

    public Borrower readBorrowersByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
        return readSingle("select * from tbl_borrower where cardNo = ?", new Object[] { cardNo });
    }

    @Override
    public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Borrower> borrowerList = new ArrayList<>();
        while (rs.next()) {
            Borrower borrower = new Borrower();
            borrower.setCardNo(rs.getInt("cardNo"));
            borrower.setName(rs.getString("name"));
            borrower.setAddress(rs.getString("address"));
            borrower.setPhone(rs.getString("phone"));
            borrowerList.add(borrower);
        }
        return borrowerList;

    }

    @Override
    Borrower extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
        if (rs.next()) {
            Borrower borrower = new Borrower();
            borrower.setCardNo(rs.getInt("cardNo"));
            borrower.setName(rs.getString("name"));
            borrower.setAddress(rs.getString("address"));
            borrower.setPhone(rs.getString("phone"));
            return borrower;
        }
        return null;
    }

}