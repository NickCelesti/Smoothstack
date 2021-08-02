package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.BookingGuest;

public class BookingGuestDao extends BaseDAO<BookingGuest> {

    public BookingGuestDao(Connection conn) {
        super(conn);
    }

    public void add(BookingGuest bookingGuest) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO booking_guest VALUES (?, ?, ?)",
                new Object[] { bookingGuest.getBookingId(), bookingGuest.getEmail(), bookingGuest.getPhone() });
    }

    public void addToEnd(BookingGuest bookingGuest) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(booking_id) FROM `booking_guest`)", null);
        save("insert into booking_guest values (@max_id + 1, ?, ?)",
                new Object[] { bookingGuest.getBookingId(), bookingGuest.getEmail(), bookingGuest.getPhone() });
    }

    public void update(BookingGuest bookingGuest) throws SQLException, ClassNotFoundException {
        this.save("UPDATE booking_guest SET contact_email = ?, contact_phone = ? where booking_id = ?",
                new Object[] { bookingGuest.getEmail(), bookingGuest.getPhone(), bookingGuest.getBookingId() });
    }

    public void delete(BookingGuest bookingGuest) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM booking_guest WHERE booking_id = ?", new Object[] { bookingGuest.getBookingId() });
    }

    public List<BookingGuest> readAllBookingGuests() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_guest", null);
    }

    public BookingGuest readBookingGuestById(Integer bookingId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from booking_guest where id = ?", new Object[] { bookingId });
    }

    @Override
    public List<BookingGuest> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<BookingGuest> airplaneList = new ArrayList<>();
        while (rs.next()) {
            BookingGuest bookingGuest = new BookingGuest();
            bookingGuest.setBookingId(rs.getInt("booking_id"));
            bookingGuest.setEmail(rs.getString("contact_email"));
            bookingGuest.setPhone(rs.getString("contact_phone"));
            airplaneList.add(bookingGuest);
        }
        return airplaneList;
    }

    @Override
    public BookingGuest extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            BookingGuest bookingGuest = new BookingGuest();
            bookingGuest.setBookingId(rs.getInt("booking_id"));
            bookingGuest.setEmail(rs.getString("contact_email"));
            bookingGuest.setPhone(rs.getString("contact_phone"));
            return bookingGuest;
        }
        return null;
    }

}