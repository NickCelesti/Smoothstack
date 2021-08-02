package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.BookingPayment;

public class BookingPaymentDao extends BaseDAO<BookingPayment> {

    public BookingPaymentDao(Connection conn) {
        super(conn);
    }

    public void add(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO booking_payment VALUES (?, ?, ?)", new Object[] { bookingPayment.getBookingId(),
                bookingPayment.getStripeId(), bookingPayment.getIsRefunded() });
    }

    public void addToEnd(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(booking_id) FROM `booking_payment`)", null);
        save("insert into booking_payment values (@max_id + 1, ?, ?)",
                new Object[] { bookingPayment.getStripeId(), bookingPayment.getIsRefunded() });
    }

    public void update(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException {
        this.save("UPDATE booking_payment SET stripe_id = ?, refunded = ? where booking_id = ?", new Object[] {
                bookingPayment.getStripeId(), bookingPayment.getIsRefunded(), bookingPayment.getBookingId() });
    }

    public void delete(BookingPayment bookingPayment) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM booking_payment WHERE booking_id = ?", new Object[] { bookingPayment.getBookingId() });
    }

    public List<BookingPayment> readAllBookingPayments() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_payment", null);
    }

    public List<BookingPayment> readBookingPaymentsByMaxCapacity(Boolean isRefunded)
            throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_payment WHERE refunded = ?", new Object[] { isRefunded });
    }

    public BookingPayment readBookingPaymentById(Integer bookingId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from booking_payment where booking_id = ?", new Object[] { bookingId });
    }

    @Override
    public List<BookingPayment> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<BookingPayment> airplaneList = new ArrayList<>();
        while (rs.next()) {
            BookingPayment bookingPayment = new BookingPayment();
            bookingPayment.setBookingId(rs.getInt("booking_id"));
            bookingPayment.setStripeId(rs.getInt("stripe_id"));
            bookingPayment.setIsRefunded(rs.getBoolean("refunded"));
            airplaneList.add(bookingPayment);
        }
        return airplaneList;
    }

    @Override
    public BookingPayment extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            BookingPayment bookingPayment = new BookingPayment();
            bookingPayment.setBookingId(rs.getInt("booking_id"));
            bookingPayment.setStripeId(rs.getInt("stripe_id"));
            bookingPayment.setIsRefunded(rs.getBoolean("refunded"));
            return bookingPayment;
        }
        return null;
    }

}