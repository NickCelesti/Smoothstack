package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import Utopia.DAO.BookingDao;
import Utopia.Domain.Booking;

public class AdminServiceBooking {

    ConnectionUtil util = new ConnectionUtil();

    public void addBooking() throws SQLException {
        Connection conn = null;
        Booking booking = new Booking();
        try {
            conn = util.getConnection();
            BookingDao bdao = new BookingDao(conn);
            Random r = new Random();

            System.out.println("Enter is_active[0, 1] ('quit to cancel'): ");
            Integer intIsActive = InputHandler.getIntInput(0, 1);
            Boolean isActive;
            if (intIsActive == 1) {
                isActive = true;
            } else {
                isActive = false;
            }
            booking.setIsActive(isActive);
            booking.setCode(String.valueOf(r.nextInt(899999) + 100000)); // generates random code 100,000 - 999,999

            bdao.addToEnd(booking);
            conn.commit();
            System.out.println("Successfully added booking");
            System.out.println("Confimation code: " + booking.getCode());
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Booking not added");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateBooking() throws SQLException {
        Connection conn = null;
        Booking booking = new Booking();
        try {
            conn = util.getConnection();
            BookingDao bdao = new BookingDao(conn);
            Random r = new Random();

            List<Booking> bookings = bdao.readAllBookings();
            int index = 1;
            for (Booking b : bookings) {
                System.out.println(
                        index + ") Id: " + b.getId() + ", Is active: " + b.getIsActive() + ", Code: " + b.getCode());
                index++;
            }
            System.out.println(index + ") Cancel");
            int bookingChoice = InputHandler.getIntInput(1, index);
            if (bookingChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            booking = bookings.get(--bookingChoice);

            System.out.println("Enter is_active(1 for yes, 0 for no, -1 for no change): ");
            Integer intIsActive = InputHandler.getIntInput(-1, 1);
            Boolean isActive;

            if (intIsActive == 1) {
                isActive = true;
                booking.setIsActive(isActive);
            } else if (intIsActive == 0) {
                isActive = false;
                booking.setIsActive(isActive);
            }

            System.out.println("Generate new code? (1 for yes, 0 for no): ");
            intIsActive = InputHandler.getIntInput(0, 1);

            if (intIsActive == 1) {
                booking.setCode(String.valueOf(r.nextInt(899999) + 100000)); // generates random code 100,000 - 999,999
            }

            bdao.update(booking);
            conn.commit();
            System.out.println("Successfully updated booking");
            System.out.println("Confimation code: " + booking.getCode());
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Booking not updated");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteBooking() throws SQLException {
        Connection conn = null;
        Booking booking = new Booking();
        try {
            conn = util.getConnection();
            BookingDao bdao = new BookingDao(conn);

            List<Booking> bookings = bdao.readAllBookings();
            int index = 1;
            for (Booking b : bookings) {
                System.out.println(
                        index + ") Id: " + b.getId() + ", Is active: " + b.getIsActive() + ", Code: " + b.getCode());
                index++;
            }
            System.out.println(index + ") Cancel");
            int bookingChoice = InputHandler.getIntInput(1, index);
            if (bookingChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            booking = bookings.get(--bookingChoice);

            bdao.delete(booking);
            conn.commit();
            System.out.println("Successfully deleted booking");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Booking not deleted");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void readAllBookings() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            BookingDao bdao = new BookingDao(conn);

            List<Booking> bookings = bdao.readAllBookings();
            int index = 1;
            for (Booking b : bookings) {
                System.out.println(
                        index + ") Id: " + b.getId() + ", Is active: " + b.getIsActive() + ", Code: " + b.getCode());
                index++;
            }

            conn.commit();
            System.out.println("Successfully read booking");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Booking not read");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
