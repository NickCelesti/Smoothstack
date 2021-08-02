package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.BookingUser;

public class BookingUserDao extends BaseDAO<BookingUser> {

    public BookingUserDao(Connection conn) {
        super(conn);
    }

    public void add(BookingUser bookingUser) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO booking_user VALUES (?, ?)",
                new Object[] { bookingUser.getBookingId(), bookingUser.getUserId() });
    }

    public void addToEnd(BookingUser bookingUser) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(booking_id) FROM `booking_user`)", null);
        save("insert into booking_user values (@max_id + 1, ?)", new Object[] { bookingUser.getUserId() });
    }

    public void update(BookingUser bookingUser) throws SQLException, ClassNotFoundException {
        this.save("UPDATE booking_user SET user_id = ? where booking_id = ?",
                new Object[] { bookingUser.getUserId(), bookingUser.getBookingId() });
    }

    public void delete(BookingUser bookingUser) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM booking_user WHERE booking_id = ?", new Object[] { bookingUser.getBookingId() });
    }

    public List<BookingUser> readAllBookingUsers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_user", null);
    }

    public List<BookingUser> readBookingUsersByUserId(Integer userId) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_user WHERE user_id = ?", new Object[] { userId });
    }

    public BookingUser readBookingUserByBookingId(Integer bookingId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from booking_user where booking_id = ?", new Object[] { bookingId });
    }

    @Override
    public List<BookingUser> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<BookingUser> airplaneList = new ArrayList<>();
        while (rs.next()) {
            BookingUser bookingUser = new BookingUser();
            bookingUser.setBookingId(rs.getInt("booking_id"));
            bookingUser.setUserId(rs.getInt("user_id"));
            airplaneList.add(bookingUser);
        }
        return airplaneList;
    }

    @Override
    public BookingUser extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            BookingUser bookingUser = new BookingUser();
            bookingUser.setBookingId(rs.getInt("booking_id"));
            bookingUser.setUserId(rs.getInt("user_id"));
            return bookingUser;
        }
        return null;
    }

}