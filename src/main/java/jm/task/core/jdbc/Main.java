package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        /*
        Map<String, Object> map = Util.getSessionFactory().getProperties();
        map.entrySet().forEach(System.out::println);
       */


        UserService userService = new UserServiceImpl();
        userService.createUsersTable();


        userService.saveUser("Ivan", "Ivanov", (byte) 19);
        userService.saveUser("Petr", "Petrov", (byte) 20);
        userService.saveUser("Сидр", "Сидоров", (byte) 21);
        userService.saveUser("Иван", "Иванов", (byte) 22);
        userService.removeUserById(2);

        userService.dropUsersTable();

        //userService.getAllUsers().forEach(System.out::println);



        /*
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 19);
        userService.saveUser("Petr", "Petrov", (byte) 20);
        userService.saveUser("Сидр", "Сидоров", (byte) 21);
        userService.saveUser("Иван", "Иванов", (byte) 22);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.removeUserById((long)1);
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
        userService.close();
        */



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
