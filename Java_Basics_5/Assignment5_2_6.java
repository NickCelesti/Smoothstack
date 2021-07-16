package Java_Basics_5;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Assignment5_2_6 {

    public static class SampleSingleton {

        // conn does nothing right now
        private static Connection conn = null;

        private static SampleSingleton instance = null;

        // i would put conn stuff in here but it needs a lot of inherent methods written
        private SampleSingleton() {
            // conn = new Connection() {}
        }

        // original class does not instanciate instance if it is null,
        // it also was not thread safe
        public static SampleSingleton getInstance() {

            if (instance == null) {
                synchronized (SampleSingleton.class) {
                    if (instance == null) {
                        instance = new SampleSingleton();
                    }
                }
            }
            return instance;
        }
    }
}
