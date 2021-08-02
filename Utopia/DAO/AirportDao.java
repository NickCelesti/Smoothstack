package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Airport;

public class AirportDao extends BaseDAO<Airport> {

    public AirportDao(Connection conn) {
        super(conn);
    }

    public void add(Airport airport) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO airport VALUES (?, ?)", new Object[] { airport.getIata_id(), airport.getCity() });
    }

    public void addToEnd(Airport airport) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(iata_id) FROM `airport`)", null);
        save("INSERT into airport VALUES (@max_id + 1, ?)", new Object[] { airport.getCity() });
    }

    public void update(Airport airport) throws SQLException, ClassNotFoundException {
        this.save("UPDATE airport SET city = ? where iata_id = ?",
                new Object[] { airport.getCity(), airport.getIata_id() });
    }

    public void delete(Airport airport) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM airport WHERE iata_id = ?", new Object[] { airport.getIata_id() });
    }

    public List<Airport> readAllAirports() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM airport", null);
    }

    public List<Airport> readAirportsByCity(String city) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM airport WHERE city = ?", new Object[] { city });
    }

    public Airport readAirportById(String airportId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from airport where iata_id = ?", new Object[] { airportId });
    }

    @Override
    public List<Airport> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Airport> airportList = new ArrayList<>();
        while (rs.next()) {
            Airport airport = new Airport();
            airport.setIata_id(rs.getString("iata_id"));
            airport.setCity(rs.getString("city"));
            airportList.add(airport);
        }
        return airportList;
    }

    @Override
    public Airport extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Airport airport = new Airport();
            airport.setIata_id(rs.getString("iata_id"));
            airport.setCity(rs.getString("city"));
            return airport;
        }
        return null;
    }

}