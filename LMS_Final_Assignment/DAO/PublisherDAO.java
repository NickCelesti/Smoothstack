package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {

    public PublisherDAO(Connection conn) {
        super(conn);
    }

    public void add(Publisher publisher) throws SQLException, ClassNotFoundException {
        save("INSERT INTO tbl_publisher VALUES (?, ?, ?, ?)",
                new Object[] { publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getPhone() });
    }

    public void addToEnd(Publisher publisher) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(publisherId) FROM `tbl_publisher`)", null);
        save("insert into tbl_publisher values (@max_id + 1, ?, ?, ?)",
                new Object[] { publisher.getName(), publisher.getAddress(), publisher.getPhone() });
    }

    public void update(Publisher publisher) throws SQLException, ClassNotFoundException {
        save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?",
                new Object[] { publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getId() });
    }

    public void delete(Publisher publisher) throws SQLException, ClassNotFoundException {
        save("DELETE FROM tbl_publisher where publisherId = ?", new Object[] { publisher.getId() });
    }

    public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
        return this.read("select * from tbl_publisher", null);
    }

    public Publisher readPublisherById(Integer publisherId) throws SQLException, ClassNotFoundException {
        return readSingle("select * from tbl_publisher where publisherId = ?", new Object[] { publisherId });
    }

    @Override
    public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<Publisher> pList = new ArrayList<>();
        while (rs.next()) {
            Publisher publisher = new Publisher();
            publisher.setId(rs.getInt("publisherId"));
            publisher.setName(rs.getString("publisherName"));
            publisher.setAddress(rs.getString("publisherAddress"));
            publisher.setPhone(rs.getString("publisherPhone"));
            pList.add(publisher);
        }

        return pList;
    }

    @Override
    public Publisher extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Publisher publisher = new Publisher();
            publisher.setId(rs.getInt("publisherId"));
            publisher.setName(rs.getString("publisherName"));
            publisher.setAddress(rs.getString("publisherAddress"));
            publisher.setPhone(rs.getString("publisherPhone"));
            return publisher;
        }

        return null;
    }

}