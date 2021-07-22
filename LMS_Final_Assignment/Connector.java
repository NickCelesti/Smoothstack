package LMS_Final_Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    // this class ensures that we don't connect to the database more than once with
    // a singleton pattern

    private Connection conn;

    private static Connector instance = null;

    private Connector() {
        try {
            conn = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC", "root", "Elk5golf");
        } catch (SQLException e) {
            System.out.println("Failed to connect to Database");
        }
    }

    public static Connector getInstance() {

        if (instance == null) {
            synchronized (Connector.class) {
                if (instance == null) {
                    instance = new Connector();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
