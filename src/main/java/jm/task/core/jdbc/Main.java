package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Александр", "Александров", (byte) 25);
        userService.saveUser("Иван", "Иванов", (byte) 35);
        userService.saveUser("Петр", "Петров", (byte) 29);
        userService.saveUser("Мария", "Мариева", (byte) 25);
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
