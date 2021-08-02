package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Passenger;

public class PassengerDao extends BaseDAO<Passenger> {

    public PassengerDao(Connection conn) {
        super(conn);
    }

    public void add(Passenger passenger) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO passenger VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Object[] { passenger.getId(), passenger.getBookingId(), passenger.getGivenName(),
                        passenger.getFamilyName(), passenger.getDateOfBirth(), passenger.getGender(),
                        passenger.getAddress() });
    }

    public void addToEnd(Passenger passenger) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `passenger`)", null);
        save("insert into passenger values (@max_id + 1, ?, ?, ?, ?, ?, ?)",
                new Object[] { passenger.getBookingId(), passenger.getGivenName(), passenger.getFamilyName(),
                        passenger.getDateOfBirth(), passenger.getGender(), passenger.getAddress() });
    }

    public void update(Passenger passenger) throws SQLException, ClassNotFoundException {
        this.save(
                "UPDATE passenger SET booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender = ?, address = ? where id = ?",
                new Object[] { passenger.getBookingId(), passenger.getGivenName(), passenger.getFamilyName(),
                        passenger.getDateOfBirth(), passenger.getGender(), passenger.getAddress(), passenger.getId() });
    }

    public void delete(Passenger passenger) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM passenger WHERE id = ?", new Object[] { passenger.getId() });
    }

    public List<Passenger> readAllPassengers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM passenger", null);
    }

    public List<Passenger> readPassengersByBookingId(Integer bookingId) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM passenger WHERE booking_id = ?", new Object[] { bookingId });
    }

    public Passenger readPassengerById(Integer id) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from passenger where id = ?", new Object[] { id });
    }

    @Override
    public List<Passenger> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Passenger> passengerList = new ArrayList<>();
        while (rs.next()) {
            Passenger passenger = new Passenger();
            passenger.setId(rs.getInt("id"));
            passenger.setBookingId(rs.getInt("booking_id"));
            passenger.setGivenName(rs.getString("given_name"));
            passenger.setFamilyName(rs.getString("family_name"));
            passenger.setDateOfBirth(rs.getDate("dob"));
            passenger.setGender(rs.getString("gender"));
            passenger.setAddress(rs.getString("address"));
            passengerList.add(passenger);
        }
        return passengerList;
    }

    @Override
    public Passenger extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Passenger passenger = new Passenger();
            passenger.setId(rs.getInt("id"));
            passenger.setBookingId(rs.getInt("booking_id"));
            passenger.setGivenName(rs.getString("given_name"));
            passenger.setFamilyName(rs.getString("family_name"));
            passenger.setDateOfBirth(rs.getDate("dob"));
            passenger.setGender(rs.getString("gender"));
            passenger.setAddress(rs.getString("address"));
            return passenger;
        }
        return null;
    }

}