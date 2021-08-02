package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Booking;

public class BookingDao extends BaseDAO<Booking> {

    public BookingDao(Connection conn) {
        super(conn);
    }

    public void add(Booking booking) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO booking VALUES (?, ?, ?)",
                new Object[] { booking.getId(), booking.getIsActive(), booking.getCode() });
    }

    public void addToEnd(Booking booking) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `booking`)", null);
        save("insert into booking values (@max_id + 1, ?, ?)",
                new Object[] { booking.getIsActive(), booking.getCode() });
    }

    public Integer addToEndReturnPK(Booking booking) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `booking`)", null);
        return saveReturnPK("insert into booking values (@max_id + 1, ?, ?)",
                new Object[] { booking.getIsActive(), booking.getCode() });
    }

    public void update(Booking booking) throws SQLException, ClassNotFoundException {
        this.save("UPDATE booking SET is_active = ?, confirmation_code = ? where id = ?",
                new Object[] { booking.getIsActive(), booking.getCode(), booking.getId() });
    }

    public void delete(Booking booking) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM booking WHERE id = ?", new Object[] { booking.getId() });
    }

    public List<Booking> readAllBookings() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking", null);
    }

    public List<Booking> readBookingsByMaxCapacity(Boolean isActive) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking WHERE is_active = ?", new Object[] { isActive });
    }

    public Booking readBookingById(Integer id) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from booking where id = ?", new Object[] { id });
    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Booking> airplaneList = new ArrayList<>();
        while (rs.next()) {
            Booking booking = new Booking();
            booking.setId(rs.getInt("id"));
            booking.setIsActive(rs.getBoolean("is_active"));
            booking.setCode(rs.getString("confirmation_code"));
            airplaneList.add(booking);
        }
        return airplaneList;
    }

    @Override
    public Booking extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Booking booking = new Booking();
            booking.setId(rs.getInt("id"));
            booking.setIsActive(rs.getBoolean("is_active"));
            booking.setCode(rs.getString("confirmation_code"));
            return booking;
        }
        return null;
    }

}