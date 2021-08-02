package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.FlightBookings;

public class FlightBookingsDao extends BaseDAO<FlightBookings> {

    public FlightBookingsDao(Connection conn) {
        super(conn);
    }

    public void add(FlightBookings flightBookings) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO flight_bookings VALUES (?, ?)",
                new Object[] { flightBookings.getFlightId(), flightBookings.getBookingId() });
    }

    public void addToEnd(FlightBookings flightBookings) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(flight_id) FROM `flight_bookings`)", null);
        save("insert into flight_bookings values (@max_id + 1, ?)", new Object[] { flightBookings.getBookingId() });
    }

    public void update(FlightBookings flightBookings) throws SQLException, ClassNotFoundException {
        this.save("UPDATE flight_bookings SET booking_id = ? where flight_id = ?",
                new Object[] { flightBookings.getBookingId(), flightBookings.getFlightId() });
    }

    public void delete(FlightBookings flightBookings) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM flight_bookings WHERE flight_id = ?", new Object[] { flightBookings.getFlightId() });
    }

    public List<FlightBookings> readAllFlightBookingss() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM flight_bookings", null);
    }

    public List<FlightBookings> readFlightBookingssByBookingId(Integer bookingId)
            throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM flight_bookings WHERE booking_id = ?", new Object[] { bookingId });
    }

    public FlightBookings readFlightBookingsByFlightId(Integer flightId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from flight_bookings where flight_id = ?", new Object[] { flightId });
    }

    @Override
    public List<FlightBookings> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<FlightBookings> airplaneList = new ArrayList<>();
        while (rs.next()) {
            FlightBookings flightBookings = new FlightBookings();
            flightBookings.setFlightId(rs.getInt("flight_id"));
            flightBookings.setBookingId(rs.getInt("booking_id"));
            airplaneList.add(flightBookings);
        }
        return airplaneList;
    }

    @Override
    public FlightBookings extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            FlightBookings flightBookings = new FlightBookings();
            flightBookings.setFlightId(rs.getInt("flight_id"));
            flightBookings.setBookingId(rs.getInt("booking_id"));
            return flightBookings;
        }
        return null;
    }

}