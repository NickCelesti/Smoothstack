package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Flight;

public class FlightDao extends BaseDAO<Flight> {

    public FlightDao(Connection conn) {
        super(conn);
    }

    public void add(Flight flight) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO flight VALUES (?, ?, ?, ?, ?, ?)", new Object[] { flight.getId(), flight.getRouteId(),
                flight.getAirplaneId(), flight.getDepartureTime(), flight.getReservedSeats(), flight.getSeatPrice() });
    }

    public void addToEnd(Flight flight) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `flight`)", null);
        save("insert into flight values (@max_id + 1, ?, ?, ?, ?, ?)", new Object[] { flight.getRouteId(),
                flight.getAirplaneId(), flight.getDepartureTime(), flight.getReservedSeats(), flight.getSeatPrice() });
    }

    public void update(Flight flight) throws SQLException, ClassNotFoundException {
        this.save(
                "UPDATE flight SET route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ? where id = ?",
                new Object[] { flight.getRouteId(), flight.getAirplaneId(), flight.getDepartureTime(),
                        flight.getReservedSeats(), flight.getSeatPrice(), flight.getId() });
    }

    public void delete(Flight flight) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM flight WHERE id = ?", new Object[] { flight.getId() });
    }

    public List<Flight> readAllFlights() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM flight", null);
    }

    public List<Flight> readFlightsByRouteId(Integer routeId) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM flight WHERE route_id = ?", new Object[] { routeId });
    }

    public List<Flight> readFlightsByAirplaneId(Integer airplaneId) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM flight WHERE airplane_id = ?", new Object[] { airplaneId });
    }

    public Flight readFlightById(Integer id) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from flight where id = ?", new Object[] { id });
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Flight> airplaneList = new ArrayList<>();
        while (rs.next()) {
            Flight flight = new Flight();
            flight.setId(rs.getInt("id"));
            flight.setRouteId(rs.getInt("route_id"));
            flight.setAirplaneId(rs.getInt("airplane_id"));
            flight.setDepartureTime(rs.getTimestamp("departure_time"));
            flight.setReservedSeats(rs.getInt("reserved_seats"));
            flight.setSeatPrice(rs.getFloat("seat_price"));
            airplaneList.add(flight);
        }
        return airplaneList;
    }

    @Override
    public Flight extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Flight flight = new Flight();
            flight.setId(rs.getInt("id"));
            flight.setRouteId(rs.getInt("route_id"));
            flight.setAirplaneId(rs.getInt("airplane_id"));
            flight.setDepartureTime(rs.getTimestamp("departure_time"));
            flight.setReservedSeats(rs.getInt("reserved_seats"));
            flight.setSeatPrice(rs.getFloat("seat_price"));
            return flight;
        }
        return null;
    }

}