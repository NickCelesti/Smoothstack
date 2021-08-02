package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import Utopia.DAO.AirplaneDao;
import Utopia.DAO.AirplaneTypeDao;
import Utopia.DAO.AirportDao;
import Utopia.DAO.FlightDao;
import Utopia.DAO.RouteDao;
import Utopia.Domain.Airplane;
import Utopia.Domain.AirplaneType;
import Utopia.Domain.Airport;
import Utopia.Domain.Flight;
import Utopia.Domain.Route;

public class AdminServiceFlight {

    ConnectionUtil util = new ConnectionUtil();

    public void addFlight() throws SQLException {
        Connection conn = null;
        Flight flight = new Flight();
        try {
            conn = util.getConnection();
            RouteDao rdao = new RouteDao(conn);
            AirportDao portDao = new AirportDao(conn);
            FlightDao fdao = new FlightDao(conn);
            AirplaneDao planeDao = new AirplaneDao(conn);
            AirplaneTypeDao planeTypeDao = new AirplaneTypeDao(conn);

            List<Route> routes = rdao.readAllRoutes();
            int index = 1;
            Airport origin, dest;
            for (Route r : routes) {
                origin = portDao.readAirportById(r.getOriginId());
                dest = portDao.readAirportById(r.getDestId());
                System.out.println(index + ") " + origin.getCity() + " -> " + dest.getCity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int routeChoice = InputHandler.getIntInput(1, index);
            if (routeChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            Route route = routes.get(--routeChoice);

            List<Airplane> planes = planeDao.readAllAirplanes();
            index = 1;
            AirplaneType planeType;
            for (Airplane a : planes) {
                planeType = planeTypeDao.readAirplaneTypeById(a.getTypeId());
                System.out
                        .println(index + ") Plane id: " + a.getId() + ", Max Capacity: " + planeType.getMaxCapacity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int planeChoice = InputHandler.getIntInput(1, index);
            if (planeChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            Airplane plane = planes.get(--planeChoice);

            System.out.println("Enter DateTime [YYYY-mm-dd hh:mm:ss] ('quit' to cancel): ");
            String dateTime = InputHandler.getStringInput();
            if (dateTime.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }
            Timestamp dt = Timestamp.valueOf(dateTime);

            System.out.println("Enter reserved seats: ");
            Integer seats = InputHandler.getCardInput();

            System.out.println("Enter seat price: ");
            String sPrice = InputHandler.getStringInput();
            if (sPrice.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }
            Float price = Float.parseFloat(sPrice);

            flight.setRouteId(route.getId());
            flight.setAirplaneId(plane.getId());
            flight.setDepartureTime(dt);
            flight.setReservedSeats(seats);
            flight.setSeatPrice(price);
            fdao.addToEnd(flight);
            conn.commit();
            System.out.println("Successfully added flight");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Flight not added");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateFlight() throws SQLException {
        Connection conn = null;
        Flight flight = new Flight();
        try {
            conn = util.getConnection();
            RouteDao rdao = new RouteDao(conn);
            AirportDao portDao = new AirportDao(conn);
            FlightDao fdao = new FlightDao(conn);
            AirplaneDao planeDao = new AirplaneDao(conn);
            AirplaneTypeDao planeTypeDao = new AirplaneTypeDao(conn);

            List<Flight> flights = fdao.readAllFlights();
            Route route;
            int index = 1;
            Airport originCity, destCity;
            for (Flight f : flights) {
                route = rdao.readRouteById(f.getRouteId());
                originCity = portDao.readAirportById(route.getOriginId());
                destCity = portDao.readAirportById(route.getDestId());
                System.out.println(index + ") " + route.getOriginId() + ", " + originCity.getCity() + " -> "
                        + route.getDestId() + ", " + destCity.getCity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int flightChoice = InputHandler.getIntInput(1, index);
            if (flightChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            flight = flights.get(--flightChoice);

            List<Route> routes = rdao.readAllRoutes();
            index = 1;
            Airport origin, dest;
            for (Route r : routes) {
                origin = portDao.readAirportById(r.getOriginId());
                dest = portDao.readAirportById(r.getDestId());
                System.out.println(index + ") " + origin.getCity() + " -> " + dest.getCity());
                index++;
            }
            System.out.println(index + ") No change");
            int routeChoice = InputHandler.getIntInput(1, index);
            if (routeChoice != index) {
                flight.setRouteId(routes.get(--routeChoice).getId());
            }

            List<Airplane> planes = planeDao.readAllAirplanes();
            index = 1;
            AirplaneType planeType;
            for (Airplane a : planes) {
                planeType = planeTypeDao.readAirplaneTypeById(a.getTypeId());
                System.out
                        .println(index + ") Plane id: " + a.getId() + ", Max Capacity: " + planeType.getMaxCapacity());
                index++;
            }
            System.out.println(index + ") No change");
            int planeChoice = InputHandler.getIntInput(1, index);
            if (planeChoice != index) {
                flight.setAirplaneId(planes.get(--planeChoice).getId());
            }

            System.out.println("Enter DateTime [YYYY-mm-dd hh:mm:ss] ('n/a' to keep current): ");
            String dateTime = InputHandler.getStringInput();
            if (dateTime.compareToIgnoreCase("n/a") != 0) {
                Timestamp dt = Timestamp.valueOf(dateTime);
                flight.setDepartureTime(dt);
            }

            System.out.println("Enter reserved seats (-1 to keep current): ");
            Integer seats = InputHandler.getCardInput();
            if (seats != -1) {
                flight.setReservedSeats(seats);
            }

            System.out.println("Enter seat price: ");
            String sPrice = InputHandler.getStringInput();
            if (sPrice.compareToIgnoreCase("n/a") != 0) {
                Float price = Float.parseFloat(sPrice);
                flight.setSeatPrice(price);
            }

            fdao.update(flight);
            conn.commit();
            System.out.println("Successfully updated flight");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Flight not updated");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteFlight() throws SQLException {
        Connection conn = null;
        Flight flight = new Flight();
        try {
            conn = util.getConnection();
            RouteDao rdao = new RouteDao(conn);
            AirportDao portDao = new AirportDao(conn);
            FlightDao fdao = new FlightDao(conn);

            List<Flight> flights = fdao.readAllFlights();
            Route route;
            int index = 1;
            Airport originCity, destCity;
            for (Flight f : flights) {
                route = rdao.readRouteById(f.getRouteId());
                originCity = portDao.readAirportById(route.getOriginId());
                destCity = portDao.readAirportById(route.getDestId());
                System.out.println(index + ") " + route.getOriginId() + ", " + originCity.getCity() + " -> "
                        + route.getDestId() + ", " + destCity.getCity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int flightChoice = InputHandler.getIntInput(1, index);
            if (flightChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            flight = flights.get(--flightChoice);

            fdao.delete(flight);
            conn.commit();
            System.out.println("Successfully deleted flight");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Flight not deleted");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void readAllFlights() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            RouteDao rdao = new RouteDao(conn);
            AirportDao portDao = new AirportDao(conn);
            FlightDao fdao = new FlightDao(conn);

            List<Flight> flights = fdao.readAllFlights();
            Route route;
            int index = 1;
            Airport originCity, destCity;
            for (Flight f : flights) {
                route = rdao.readRouteById(f.getRouteId());
                originCity = portDao.readAirportById(route.getOriginId());
                destCity = portDao.readAirportById(route.getDestId());
                System.out.println(index + ") " + route.getOriginId() + ", " + originCity.getCity() + " -> "
                        + route.getDestId() + ", " + destCity.getCity());
                index++;
            }

            conn.commit();
            System.out.println("Successfully displayed flights");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Flight not displayed");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
