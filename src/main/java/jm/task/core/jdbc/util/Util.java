package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/PreProjectDB";
    private static final String USER = "user";
    private static final String PASSWORD = "user";

    public Util() {}

    public  Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, USER);
            properties.put(Environment.PASS, PASSWORD);
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//            properties.put(Environment.HBM2DDL_AUTO, "");
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration.buildSessionFactory();
    }
}
