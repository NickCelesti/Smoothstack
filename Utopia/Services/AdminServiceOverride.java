package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Utopia.DAO.BookingDao;
import Utopia.Domain.Booking;

public class AdminServiceOverride {

    ConnectionUtil util = new ConnectionUtil();

    public void overrideCancelation() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            BookingDao bdao = new BookingDao(conn);

            List<Booking> bookings = bdao.readAllBookings();

            int index = 1;
            for (Booking b : bookings) {
                System.out.println(
                        index + ") Id: " + b.getId() + ", Is Active: " + b.getIsActive() + ", Code: " + b.getCode());
                index++;
            }
            System.out.println(index + ") Cancel");
            int bookingChoice = InputHandler.getIntInput(1, index);
            if (bookingChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            Booking booking = bookings.get(--bookingChoice);
            System.out.println("Is Active: " + booking.getIsActive());
            System.out.println("Override value (1 for true, 0 for false):");
            int newChoice = InputHandler.getIntInput(0, 1);
            Boolean isActive;
            if (newChoice == 0) {
                isActive = false;
            } else {
                isActive = true;
            }
            booking.setIsActive(isActive);
            bdao.update(booking);
            conn.commit();
            System.out.println("Successfully wrote value");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("value not added");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
