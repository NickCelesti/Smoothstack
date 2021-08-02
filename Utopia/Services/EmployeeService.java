package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.DAO.AirportDao;
import Utopia.DAO.FlightDao;
import Utopia.DAO.RouteDao;
import Utopia.Domain.Airport;
import Utopia.Domain.Flight;
import Utopia.Domain.Route;

public class EmployeeService {

    ConnectionUtil util = new ConnectionUtil();

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

    public List<Airport> readAllAirports() throws SQLException {
        Connection conn = null;
        List<Airport> airports = new ArrayList<>();
        try {
            conn = util.getConnection();
            AirportDao adao = new AirportDao(conn);
            airports = adao.readAllAirports();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return airports;
    }

    public void updateFlight(Flight flight, Route route) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            FlightDao fdao = new FlightDao(conn);
            RouteDao rdao = new RouteDao(conn);
            fdao.update(flight);
            rdao.addToEnd(route);

            conn.commit();
            System.out.println("Successfully updated flight");
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

    public void updateFlight(Flight flight) throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            FlightDao fdao = new FlightDao(conn);
            fdao.update(flight);

            conn.commit();
            System.out.println("Successfully updated flight");
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
}
