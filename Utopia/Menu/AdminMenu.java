package Utopia.Menu;

import java.sql.SQLException;

import Utopia.Services.InputHandler;
import Utopia.Services.AdminServiceAirport;
import Utopia.Services.AdminServiceBooking;
import Utopia.Services.AdminServiceFlight;
import Utopia.Services.AdminServiceOverride;
import Utopia.Services.AdminServicePassenger;
import Utopia.Services.AdminServiceUser;

public class AdminMenu {

    public static void menuAdmin1() throws SQLException {

        System.out.println("1) Add/Update/Delete/Read Flights");
        System.out.println("2) Add/Update/Delete/Read Passengers");
        System.out.println("3) Add/Update/Delete/Read Bookings");
        System.out.println("4) Add/Update/Delete/Read Airports");
        System.out.println("5) Add/Update/Delete/Read Users");
        System.out.println("6) Over-ride Trip Cancellation for a ticket");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 6);

        if (ans == 1) {
            crudFlights();
        } else if (ans == 2) {
            crudPassengers();
        } else if (ans == 3) {
            crudBookings();
        } else if (ans == 4) {
            crudAirports();
        } else if (ans == 5) {
            crudUsers();
        } else if (ans == 6) {
            override();
        }
        MainMenu.menuMain();
    }

    private static void crudFlights() throws SQLException {
        AdminServiceFlight flightService = new AdminServiceFlight();
        System.out.println("1) Add Flight");
        System.out.println("2) Update Flight");
        System.out.println("3) Delete Flight");
        System.out.println("4) Read all Flights");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            flightService.addFlight();
        } else if (ans == 2) {
            flightService.updateFlight();
        } else if (ans == 3) {
            flightService.deleteFlight();
        } else if (ans == 4) {
            flightService.readAllFlights();
        }
    }

    private static void crudPassengers() throws SQLException {
        AdminServicePassenger passengerService = new AdminServicePassenger();
        System.out.println("1) Add Passenger");
        System.out.println("2) Update Passenger");
        System.out.println("3) Delete Passenger");
        System.out.println("4) Read all Passengers");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            passengerService.addPassenger();
        } else if (ans == 2) {
            passengerService.updatePassenger();
        } else if (ans == 3) {
            passengerService.deletePassenger();
        } else if (ans == 4) {
            passengerService.readAllPassengers();
        }
    }

    private static void crudBookings() throws SQLException {
        AdminServiceBooking bookingService = new AdminServiceBooking();
        System.out.println("1) Add Booking");
        System.out.println("2) Update Booking");
        System.out.println("3) Delete Booking");
        System.out.println("4) Read all Bookings");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            bookingService.addBooking();
        } else if (ans == 2) {
            bookingService.updateBooking();
        } else if (ans == 3) {
            bookingService.deleteBooking();
        } else if (ans == 4) {
            bookingService.readAllBookings();
        }
    }

    private static void crudAirports() throws SQLException {
        AdminServiceAirport airportService = new AdminServiceAirport();
        System.out.println("1) Add Airport");
        System.out.println("2) Update Airport");
        System.out.println("3) Delete Airport");
        System.out.println("4) Read all Airports");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            airportService.addAirport();
        } else if (ans == 2) {
            airportService.updateAirport();
        } else if (ans == 3) {
            airportService.deleteAirport();
        } else if (ans == 4) {
            airportService.readAllAirports();
        }
    }

    private static void crudUsers() throws SQLException {
        AdminServiceUser userService = new AdminServiceUser();
        System.out.println("1) Add User");
        System.out.println("2) Update User");
        System.out.println("3) Delete User");
        System.out.println("4) Read all Users");

        System.out.println();

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            userService.addUser();
        } else if (ans == 2) {
            userService.updateUser();
        } else if (ans == 3) {
            userService.deleteUser();
        } else if (ans == 4) {
            userService.readAllUsers();
        }
    }

    private static void override() throws SQLException {
        AdminServiceOverride overrideService = new AdminServiceOverride();

        overrideService.overrideCancelation();
    }

}
