package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Statement statement = Util.getStatement();
    private final String tableName = "users";
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " + tableName +
                            " (Id INT PRIMARY KEY AUTO_INCREMENT," +
                            " name VARCHAR(255)," +
                            " lastName VARCHAR(255)," +
                            " age TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS %s".formatted(tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate(
                    "INSERT %s (name, lastName, age) VALUES ('%s', '%s', %d)".
                            formatted(tableName, name, lastName, age));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate(
                    "DELETE FROM %s WHERE Id = %d".formatted(tableName, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("TRUNCATE TABLE " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
