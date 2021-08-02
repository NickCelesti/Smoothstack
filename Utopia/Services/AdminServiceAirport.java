package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Utopia.DAO.AirportDao;
import Utopia.Domain.Airport;

public class AdminServiceAirport {

    ConnectionUtil util = new ConnectionUtil();

    public void addAirport() throws SQLException {
        Connection conn = null;
        Airport airport = new Airport();
        try {
            conn = util.getConnection();
            AirportDao adao = new AirportDao(conn);
            System.out.println("Enter Airport Code [XYZ] ('quit' to cancel): ");
            String code = InputHandler.getStringInput();
            if (code.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter City Name ('quit' to cancel): ");
            String city = InputHandler.getStringInput();
            if (city.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            airport.setIata_id(code);
            airport.setCity(city);
            adao.add(airport);
            conn.commit();
            System.out.println("Successfully added airport");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Airport not added");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateAirport() throws SQLException {
        Connection conn = null;
        Airport airport = new Airport();
        try {
            conn = util.getConnection();
            AirportDao adao = new AirportDao(conn);

            List<Airport> airports = adao.readAllAirports();
            int index = 1;
            for (Airport a : airports) {
                System.out.println(index + ") " + a.getIata_id() + ", " + a.getCity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int airportChoice = InputHandler.getIntInput(1, index);
            if (airportChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            airport = airports.get(--airportChoice);

            System.out.println("Enter New City Name ('n/a' for no change): ");
            String city = InputHandler.getStringInput();
            if (city.compareToIgnoreCase("quit") != 0) {
                airport.setCity(city);
            }

            adao.update(airport);
            conn.commit();
            System.out.println("Successfully updated airport");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Airport not updated");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteAirport() throws SQLException {
        Connection conn = null;
        Airport airport = new Airport();
        try {
            conn = util.getConnection();
            AirportDao adao = new AirportDao(conn);

            List<Airport> airports = adao.readAllAirports();
            int index = 1;
            for (Airport a : airports) {
                System.out.println(index + ") " + a.getIata_id() + ", " + a.getCity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int airportChoice = InputHandler.getIntInput(1, index);
            if (airportChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            airport = airports.get(--airportChoice);

            adao.delete(airport);
            conn.commit();
            System.out.println("Successfully deleted airport");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Airport not deleted");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void readAllAirports() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            AirportDao adao = new AirportDao(conn);

            List<Airport> airports = adao.readAllAirports();
            int index = 1;
            for (Airport a : airports) {
                System.out.println(index + ") " + a.getIata_id() + ", " + a.getCity());
                index++;
            }
            System.out.println(index + ") Cancel");
            int airportChoice = InputHandler.getIntInput(1, index);
            if (airportChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }

            conn.commit();
            System.out.println("Successfully read airports");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Airports not read");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
