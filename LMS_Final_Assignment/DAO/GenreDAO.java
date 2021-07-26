package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import LMS_Final_Assignment.Domain.Genre;

public class GenreDAO extends BaseDAO<Genre> {

    public GenreDAO(Connection conn) {
        super(conn);
    }

    @Override
    List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    Genre extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
        return null;
    }

    // @Override
    // List<Genre> extract(ResultSet resultSet) throws SQLException,
    // ClassNotFoundException {
    // return null;
    // }

}
