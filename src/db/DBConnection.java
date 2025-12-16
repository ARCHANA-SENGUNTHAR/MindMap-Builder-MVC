package db;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection conn;

    public static void init() {
        try (InputStream in = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("resources/config.properties")) {

            if (in == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            Properties props = new Properties();
            props.load(in);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to DB");

        } catch (Exception e) {
            System.err.println("DB init error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void close() {
        try {
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
