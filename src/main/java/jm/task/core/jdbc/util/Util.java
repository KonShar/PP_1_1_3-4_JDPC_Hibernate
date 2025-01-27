package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/PreProjectDB";
    private static final String USER = "user";
    private static final String PASSWORD = "user";

    private Util() {}

    public static Statement getStatement() {
        try {
            return Util.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
