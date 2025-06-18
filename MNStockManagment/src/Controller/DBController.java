package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBController {
	private static final String URL = "jdbc:mysql://localhost:3306/mnstock?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root"; // update if different
    private static final String PASSWORD = "Aa123456";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to mnstock database!");
            } catch (SQLException e) {
                System.err.println("Database connection failed:");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
