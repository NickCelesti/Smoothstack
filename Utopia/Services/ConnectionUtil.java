package Utopia.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utopia?useSSL=false&serverTimezone=UTC", "root", "Elk5golf");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Failed to connect to Database");
        }
        return conn;
    }

    public String getProperty(String propName) {
        Properties dbProp = new Properties();
        try (InputStream is = new FileInputStream("resources/db.properties");) {
            dbProp.load(is);
            return dbProp.getProperty(propName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
