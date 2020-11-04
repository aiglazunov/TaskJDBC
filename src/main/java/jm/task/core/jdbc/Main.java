package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        /*
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 19);
        userService.saveUser("Petr", "Petrov", (byte) 20);
        userService.saveUser("Сидр", "Сидоров", (byte) 21);
        userService.saveUser("Иван", "Иванов", (byte) 22);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

         */
        //userService.dropUsersTable();
        //userService.close();


        /*
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("name", "lastname", (byte) 12);

        List<User> list = userDao.getAllUsers();
        for (User user : list) {
            System.out.println("User id:" + user.getId()
                    + " (name: " + user.getName()
                    + ", lastname: " + user.getLastName()
                    + ", age: " + user.getAge() + ")");
        }
        //userDao.removeUserById(2);
        //userDao.cleanUsersTable();
        //userDao.dropUsersTable();
        */


    }
}
