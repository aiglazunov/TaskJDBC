package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/jm?serverTimezone=UTC";
    private static final String USERNAME = "jm";
    private static final String PASSWORD = "1Qaz2Wsx";

    private static Connection connection = null;

    public static Connection getConnection() {
        

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection Ok!");
            }
        } catch (SQLException ex) {
            System.out.println("Connection Error!");
            ex.getStackTrace();
        }


        return connection;
    }
}
