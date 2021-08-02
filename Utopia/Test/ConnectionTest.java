package Utopia.Test;

import org.junit.Test;

import Utopia.Services.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    ConnectionUtil util = new ConnectionUtil();

    @Test
    public void queryTest() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            // todo: make test

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
