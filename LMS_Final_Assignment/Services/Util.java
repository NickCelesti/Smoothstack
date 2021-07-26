package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC", "root", "Elk5golf");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Failed to connect to Database");
        }
        return conn;
    }
}
