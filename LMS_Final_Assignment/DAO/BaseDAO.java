package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {

    protected Connection conn = null;

    public BaseDAO(Connection conn) {
        this.conn = conn;
    }

    protected Integer saveReturnPK(String sql, Object[] values) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if (values != null) {
            int index = 1;
            for (Object o : values) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next())
            return rs.getInt(1);
        return null;
    }

    protected void save(String sql, Object[] values) throws SQLException, ClassNotFoundException {

        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (values != null) {
            int index = 1;
            for (Object o : values) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        pstmt.executeUpdate();
    }

    protected T readSingle(String sql, Object[] values) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (values != null) {
            int index = 1;
            for (Object o : values) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        return extractSingleData(pstmt.executeQuery());
    }

    protected List<T> read(String sql, Object[] values) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (values != null) {
            int index = 1;
            for (Object o : values) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        return extractData(pstmt.executeQuery());

    }

    abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;

    abstract T extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException;
}
