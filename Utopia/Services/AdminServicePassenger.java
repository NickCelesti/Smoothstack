package Utopia.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Utopia.DAO.BookingDao;
import Utopia.DAO.PassengerDao;
import Utopia.Domain.Booking;
import Utopia.Domain.Passenger;

public class AdminServicePassenger {

    ConnectionUtil util = new ConnectionUtil();

    public void addPassenger() throws SQLException {
        Connection conn = null;
        Passenger p = new Passenger();
        try {
            conn = util.getConnection();
            PassengerDao pdao = new PassengerDao(conn);
            BookingDao bdao = new BookingDao(conn);

            List<Booking> bookings = bdao.readAllBookings();
            int index = 1;
            for (Booking b : bookings) {
                System.out.println(
                        index + ") id:" + b.getId() + ", is active: " + b.getIsActive() + ", code: " + b.getCode());
                index++;
            }
            System.out.println(index + ") Cancel");
            int bookingChoice = InputHandler.getIntInput(1, index);
            if (bookingChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            Booking booking = bookings.get(--bookingChoice);

            System.out.println("Enter given name ('quit' to cancel): ");
            String givenName = InputHandler.getStringInput();
            if (givenName.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter family name ('quit' to cancel): ");
            String familyName = InputHandler.getStringInput();
            if (familyName.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter Date of birth [YYYY-mm-dd] ('quit' to cancel): ");
            String d = InputHandler.getStringInput();
            if (d.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }
            Date date = Date.valueOf(d);

            System.out.println("Enter gender ('quit' to cancel): ");
            String gender = InputHandler.getStringInput();
            if (gender.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter address name ('quit' to cancel): ");
            String address = InputHandler.getStringInput();
            if (address.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            p.setBookingId(booking.getId());
            p.setGivenName(givenName);
            p.setFamilyName(familyName);
            p.setDateOfBirth(date);
            p.setGender(gender);
            p.setAddress(address);
            pdao.addToEnd(p);
            conn.commit();
            System.out.println("Successfully added passenger");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Passenger not added");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updatePassenger() throws SQLException {
        Connection conn = null;
        Passenger p = new Passenger();
        try {
            conn = util.getConnection();
            PassengerDao pdao = new PassengerDao(conn);
            BookingDao bdao = new BookingDao(conn);

            List<Passenger> passengers = pdao.readAllPassengers();
            int index = 1;
            for (Passenger pa : passengers) {
                System.out.println(index + ") Name: " + pa.getGivenName() + " " + pa.getFamilyName() + ", DOB: "
                        + pa.getDateOfBirth());
                index++;
            }
            System.out.println(index + ") Cancel");
            int passChoice = InputHandler.getIntInput(1, index);
            if (passChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            p = passengers.get(--passChoice);

            List<Booking> bookings = bdao.readAllBookings();
            index = 1;
            for (Booking b : bookings) {
                System.out.println(
                        index + ") id:" + b.getId() + ", is active: " + b.getIsActive() + ", code: " + b.getCode());
                index++;
            }
            System.out.println(index + ") No change");
            int bookingChoice = InputHandler.getIntInput(1, index);
            if (bookingChoice != index) {
                Booking booking = bookings.get(--bookingChoice);
                p.setBookingId(booking.getId());
            }

            System.out.println("Enter given name ('n/a' for no change): ");
            String givenName = InputHandler.getStringInput();
            if (givenName.compareToIgnoreCase("n/a") != 0) {
                p.setGivenName(givenName);
            }

            System.out.println("Enter family name ('n/a' for no change): ");
            String familyName = InputHandler.getStringInput();
            if (familyName.compareToIgnoreCase("n/a") != 0) {
                p.setFamilyName(familyName);
            }

            System.out.println("Enter Date of birth [YYYY-mm-dd] ('n/a' for no change): ");
            String d = InputHandler.getStringInput();
            if (d.compareToIgnoreCase("n/a") != 0) {
                Date date = Date.valueOf(d);
                p.setDateOfBirth(date);
            }

            System.out.println("Enter gender ('n/a' for no change): ");
            String gender = InputHandler.getStringInput();
            if (gender.compareToIgnoreCase("n/a") != 0) {
                p.setGender(gender);
            }

            System.out.println("Enter address name ('n/a' for no change): ");
            String address = InputHandler.getStringInput();
            if (address.compareToIgnoreCase("n/a") != 0) {
                p.setAddress(address);
            }

            pdao.update(p);
            conn.commit();
            System.out.println("Successfully updated passenger");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Passenger not updated");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deletePassenger() throws SQLException {
        Connection conn = null;
        Passenger p = new Passenger();
        try {
            conn = util.getConnection();
            PassengerDao pdao = new PassengerDao(conn);

            List<Passenger> passengers = pdao.readAllPassengers();
            int index = 1;
            for (Passenger pa : passengers) {
                System.out.println(index + ") Name: " + pa.getGivenName() + " " + pa.getFamilyName() + ", DOB: "
                        + pa.getDateOfBirth());
                index++;
            }
            System.out.println(index + ") Cancel");
            int passChoice = InputHandler.getIntInput(1, index);
            if (passChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            p = passengers.get(--passChoice);

            pdao.delete(p);
            conn.commit();
            System.out.println("Successfully deleted passenger");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Passenger not deleted");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void readAllPassengers() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            PassengerDao pdao = new PassengerDao(conn);

            List<Passenger> passengers = pdao.readAllPassengers();
            int index = 1;
            for (Passenger pa : passengers) {
                System.out.println(index + ") Name: " + pa.getGivenName() + " " + pa.getFamilyName() + ", DOB: "
                        + pa.getDateOfBirth());
                index++;
            }

            conn.commit();
            System.out.println("Successfully displayed passengers");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Passengers not displayed");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
