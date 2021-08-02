package Utopia.Menu;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Airport;
import Utopia.Domain.Flight;
import Utopia.Domain.Route;
import Utopia.Services.EmployeeService;
import Utopia.Services.InputHandler;

public class EmployeeMenu {

    private static EmployeeService service = new EmployeeService();

    public static void menuEmp1() throws SQLException {
        System.out.println("1) Enter Flights you manage");
        System.out.println("2) Quit to previous");
        System.out.println();

        int ans = InputHandler.getIntInput(1, 2);

        if (ans == 1) {
            menuEmp2();
        } else {
            MainMenu.menuMain();
        }
    }

    private static void menuEmp2() throws SQLException {
        List<Flight> flights = new ArrayList<>();

        flights = service.readAllFlights();
        Route route;
        int index = 1;
        String originCity, destCity;
        for (Flight f : flights) {
            route = service.readRouteFromId(f.getRouteId());
            originCity = service.readCityNameFrontId(route.getOriginId());
            destCity = service.readCityNameFrontId(route.getDestId());
            System.out.println(index + ") " + route.getOriginId() + ", " + originCity + " -> " + route.getDestId()
                    + ", " + destCity);
            index++;
        }

        System.out.println(index + ") Quit to previous");

        int row = InputHandler.getIntInput(1, index);

        if (row == index) {
            menuEmp1();
        } else {
            menuEmp3(flights.get(row - 1));
        }
    }

    private static void menuEmp3(Flight flight) throws SQLException {
        System.out.println("1) View more details about the flight");
        System.out.println("2) Update the details of the flight");
        System.out.println("3) Add seats to flight");
        System.out.println("4) Quit to previous");

        int ans = InputHandler.getIntInput(1, 4);

        if (ans == 1) {
            viewDetails(flight);
        } else if (ans == 2) {
            updateFlight(flight);
        } else if (ans == 3) {
            addSeats(flight);
        } else {
            menuEmp2();
        }
    }

    public static void viewDetails(Flight flight) throws SQLException {
        Route route = service.readRouteFromId(flight.getRouteId());
        String originAirport = service.readCityNameFrontId(route.getOriginId());
        String destAirport = service.readCityNameFrontId(route.getDestId());

        System.out.println("You have chosen to update the Flight with Flight Id: " + flight.getId() + ".");

        System.out.println();
        System.out.println("Departure Airport: " + originAirport + " | Arrival Airport: " + destAirport);
        System.out.println("Departure Date: " + flight.getDepartureTime().toString());
        System.out.println("Reserved Seats: " + flight.getReservedSeats());
        System.out.println("Seat Price: $" + flight.getSeatPrice());

        menuEmp3(flight);
        return;
    }

    private static void updateFlight(Flight flight) throws SQLException {

        Route route = service.readRouteFromId(flight.getRouteId());
        String arrivalAirport = service.readCityNameFrontId(route.getOriginId());
        String departureAirport = service.readCityNameFrontId(route.getDestId());

        System.out.println("You have chosen to update the Flight with Flight Id: " + flight.getId()
                + " and flight origin: " + arrivalAirport + " and flight destination: " + departureAirport);

        System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
        System.out.println("Please enter new Origin Airport and City:");
        List<Airport> airports = service.readAllAirports();
        int index = 1;
        for (Airport a : airports) {
            System.out.println(index + ") " + a.getIata_id() + ", " + a.getCity());
            index++;
        }
        System.out.println(index + ") Remain the same");
        int row = InputHandler.getIntInput(1, index);

        if (row != index) {
            route.setOriginId(airports.get(--row).getIata_id());
        }

        System.out.println("Please enter new Destination Airport and City:");
        index = 1;
        for (Airport a : airports) {
            System.out.println(index + ") " + a.getIata_id() + ", " + a.getCity());
            index++;
        }
        System.out.println(index + ") Remain the same");
        row = InputHandler.getIntInput(1, index);

        if (row != index) {
            route.setDestId(airports.get(--row).getIata_id());
        }

        System.out.println("Please enter new Departure Date and time or enter N/A for no change:");
        String newDateTime = InputHandler.getStringInput();

        if (newDateTime.compareToIgnoreCase("quit") == 0) {
            System.out.println("Operation Cancelled");
            menuEmp3(flight);
            return;
        } else if (newDateTime.compareToIgnoreCase("N/A") != 0) {
            flight.setDepartureTime(Timestamp.valueOf(newDateTime));
        }

        service.updateFlight(flight, route);

        menuEmp3(flight);
        return;
    }

    private static void addSeats(Flight flight) throws SQLException {

        System.out.println("Existing number of reserved seats: " + flight.getReservedSeats());

        System.out.println("Enter new number of seats:");
        Integer newSeats = InputHandler.getIntInput(0, 500);

        flight.setReservedSeats(newSeats);

        service.updateFlight(flight);

        menuEmp3(flight);
        return;
    }
}
