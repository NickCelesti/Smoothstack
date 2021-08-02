package Utopia.Menu;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import Utopia.Services.InputHandler;
import Utopia.Domain.Booking;
import Utopia.Domain.Flight;
import Utopia.Domain.Passenger;
import Utopia.Domain.Route;
import Utopia.Services.TravelerService;

public class TravelerMenu {

    private static TravelerService service = new TravelerService();

    public static void menuTrav0() throws SQLException {
        // Assuming the membership number is the id of the passenger
        List<Passenger> users = service.readAllUsers();

        List<Integer> userNos = users.stream().map(n -> (n.getId())).collect(Collectors.toList());

        System.out.println("Enter your Membership Number: ");
        int id = 0;
        while (true) {
            id = InputHandler.getCardInput();
            if (userNos.contains(id)) {
                System.out.println("Accepted");
                break;
            } else {
                System.out.println("Membership number not in system, try again");
            }
        }
        menuTrav1(id);
    }

    private static void menuTrav1(int memberId) throws SQLException {

        System.out.println("1) Book a Ticket");
        System.out.println("2) Cancel an Upcoming Trip");
        System.out.println("3) Quit to previous");

        int ans = InputHandler.getIntInput(1, 3);

        if (ans == 1) {
            bookTicket(memberId);
        } else if (ans == 2) {
            cancelTrip(memberId);
        } else {
            MainMenu.menuMain();
        }
    }

    private static void bookTicket(int memberId) throws SQLException {
        List<Flight> flights = service.readAllFlights();

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

        int flightRow = InputHandler.getIntInput(1, index);
        Flight flight = flights.get(--flightRow);
        route = service.readRouteFromId(flight.getRouteId());
        String originAirport = service.readCityNameFrontId(route.getOriginId());
        String destAirport = service.readCityNameFrontId(route.getDestId());

        System.out.println("You have chosen to update the Flight with Flight Id: " + flight.getId() + ".");

        System.out.println();
        System.out.println("Departure Airport: " + originAirport + " | Arrival Airport: " + destAirport);
        System.out.println("Departure Date: " + flight.getDepartureTime().toString());
        System.out.println("Reserved Seats: " + flight.getReservedSeats());
        System.out.println("Seat Price: $" + flight.getSeatPrice());
        service.bookTicket(flight, memberId);
        menuTrav1(memberId);
    }

    private static void cancelTrip(int memberId) throws SQLException {
        Booking booking = service.readBookingFromMemberId(memberId);
        List<Flight> flights = service.getFlightsWithBooking(booking.getId());

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

        int flightRow = InputHandler.getIntInput(1, index);
        if (flightRow != index) {
            Flight flight = flights.get(--flightRow);
            service.deleteFlightBooking(flight, booking);
        }
        menuTrav1(memberId);
    }
}
