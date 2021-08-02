package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.BookingAgent;

public class BookingAgentDao extends BaseDAO<BookingAgent> {

    public BookingAgentDao(Connection conn) {
        super(conn);
    }

    public void add(BookingAgent bookingAgent) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO booking_agent VALUES (?, ?)",
                new Object[] { bookingAgent.getBookingId(), bookingAgent.getAgentId() });
    }

    public void update(BookingAgent bookingAgent) throws SQLException, ClassNotFoundException {
        this.save("UPDATE booking_agent SET agent_id = ? where booking_id = ?",
                new Object[] { bookingAgent.getAgentId(), bookingAgent.getBookingId() });
    }

    public void delete(BookingAgent bookingAgent) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM booking_agent WHERE booking_id = ?", new Object[] { bookingAgent.getBookingId() });
    }

    public List<BookingAgent> readAllBookingAgents() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_agent", null);
    }

    public List<BookingAgent> readBookingAgentsByAgentId(Integer agentId) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM booking_agent WHERE agent_id = ?", new Object[] { agentId });
    }

    public BookingAgent readBookingAgentByBooking(Integer bookingId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from booking_agent where booking_id = ?", new Object[] { bookingId });
    }

    @Override
    public List<BookingAgent> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<BookingAgent> bookingList = new ArrayList<>();
        while (rs.next()) {
            BookingAgent bookingAgent = new BookingAgent();
            bookingAgent.setBookingId(rs.getInt("booking_id"));
            bookingAgent.setAgentId(rs.getInt("agent_id"));
            bookingList.add(bookingAgent);
        }
        return bookingList;
    }

    @Override
    public BookingAgent extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            BookingAgent bookingAgent = new BookingAgent();
            bookingAgent.setBookingId(rs.getInt("booking_id"));
            bookingAgent.setAgentId(rs.getInt("agent_id"));
            return bookingAgent;
        }
        return null;
    }
}