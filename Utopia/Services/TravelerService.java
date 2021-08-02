package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.DAO.AirportDao;
import Utopia.DAO.BookingDao;
import Utopia.DAO.FlightBookingsDao;
import Utopia.DAO.FlightDao;
import Utopia.DAO.PassengerDao;
import Utopia.DAO.RouteDao;
import Utopia.Domain.Airport;
import Utopia.Domain.Booking;
import Utopia.Domain.Flight;
import Utopia.Domain.FlightBookings;
import Utopia.Domain.Passenger;
import Utopia.Domain.Route;

public class TravelerService {

    ConnectionUtil util = new ConnectionUtil();

    public List<Passenger> readAllUsers() throws SQLException {
        Connection conn = null;
        List<Passenger> users = new ArrayList<>();
        try {
            conn = util.getConnection();
            PassengerDao pdao = new PassengerDao(conn);
            users = pdao.readAllPassengers();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }

    public List<Flight> readAllFlights() throws SQLException {
        Connection conn = null;
        List<Flight> flights = new ArrayList<>();
        try {
            conn = util.getConnection();
            FlightDao fdao = new FlightDao(conn);
            flights = fdao.readAllFlights();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return flights;
    }

    public Route readRouteFromId(Integer routeId) throws SQLException {
        Connection conn = null;
        Route route = new Route();
        try {
            conn = util.getConnection();

            RouteDao rdao = new RouteDao(conn);
            route = rdao.readRouteById(routeId);
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return route;
    }

    public String readCityNameFrontId(String airportId) throws SQLException {
        Connection conn = null;
        String city = "";
        try {
            conn = util.getConnection();

            AirportDao adao = new AirportDao(conn);
            Airport airport = adao.readAirportById(airportId);
            city = airport.getCity();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return city;
    }

    public void bookTicket(Flight flight, int memberId) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            FlightDao fdao = new FlightDao(conn);
            BookingDao bdao = new BookingDao(conn);
            FlightBookingsDao fbdao = new FlightBookingsDao(conn);
            PassengerDao pdao = new PassengerDao(conn);
            Passenger p = pdao.readPassengerById(memberId);
            Booking booking = bdao.readBookingById(p.getBookingId());
            FlightBookings fb = new FlightBookings();
            fb.setBookingId(booking.getId());
            fb.setFlightId(flight.getId());

            fbdao.add(fb);
            flight.setReservedSeats(flight.getReservedSeats() - 1);
            fdao.update(flight);

            conn.commit();
            System.out.println("Successfully booked ticket");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Failed to update flight");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Booking readBookingFromMemberId(Integer memberId) throws SQLException {
        Connection conn = null;
        Booking booking = new Booking();
        try {
            conn = util.getConnection();
            PassengerDao pdao = new PassengerDao(conn);
            BookingDao bdao = new BookingDao(conn);
            Passenger p = pdao.readPassengerById(memberId);
            booking = bdao.readBookingById(p.getBookingId());
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return booking;
    }

    public List<Flight> getFlightsWithBooking(Integer bookingId) throws SQLException {
        Connection conn = null;
        List<Flight> flights = new ArrayList<>();
        try {
            conn = util.getConnection();
            FlightBookingsDao fbdao = new FlightBookingsDao(conn);
            FlightDao fdao = new FlightDao(conn);
            List<FlightBookings> flightBookings = fbdao.readFlightBookingssByBookingId(bookingId);
            for (FlightBookings fb : flightBookings) {
                flights.add(fdao.readFlightById(fb.getFlightId()));
            }
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return flights;
    }

    public void deleteFlightBooking(Flight flight, Booking booking) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            FlightDao fdao = new FlightDao(conn);
            FlightBookingsDao fbdao = new FlightBookingsDao(conn);
            FlightBookings fb = new FlightBookings();
            fb.setBookingId(booking.getId());
            fb.setFlightId(flight.getId());
            fbdao.delete(fb);
            flight.setReservedSeats(flight.getReservedSeats() + 1);
            fdao.update(flight);
            conn.commit();
            System.out.println("Successfully cancelled trip");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Failed to cancel trip");

        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
