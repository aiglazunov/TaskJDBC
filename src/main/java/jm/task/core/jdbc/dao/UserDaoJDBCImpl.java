package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    private ResultSet executeQuerySQL(String sql) {
        ResultSet resultSet = null;
        try  {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException ex) {
            close();
            System.out.println("ERROR in query execution");
            ex.printStackTrace();
            return null;
        }
    }

    private int executeUpdateSQL(String sql) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException ex) {
            close();
            System.out.println("ERROR in query execution");
            ex.printStackTrace();
            return -1;
        }

    }

    public void close() {
        try {
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException ex) {
            System.out.println("ERROR in connection closing");
            ex.printStackTrace();
        }
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "\n (" +
                "\n  id INTEGER NOT NULL AUTO_INCREMENT" +
                "\n ,name varchar(100) NULL" +
                "\n ,lastname varchar(100) NULL" +
                "\n ,age INTEGER null" +
                "\n ,PRIMARY KEY (id)" +
                "\n )";
        //System.out.println(sql);
        System.out.println("Таблица Users создана.");
        int result = executeUpdateSQL(sql);

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        System.out.println(sql);
        executeUpdateSQL(sql);

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (name, lastname, age)" +
                "\n VALUES (" + "'" + name + "', '" + lastName + "'" + ", " + age + ")";
        //System.out.println(sql);
        int result = executeUpdateSQL(sql);
        if (result > 0) {
            System.out.println("User c именем " + name + " добавлен в БД");
        }
        else {
            System.out.println("User не добавлен");
        }


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS" +
                "\n WHERE ID = " + id;
        System.out.println(sql);
        int result = executeUpdateSQL(sql);
        System.out.println(result);

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastname, age FROM USERS";

        try {
            ResultSet rs = executeQuerySQL(sql);
            while (rs.next()) {
                users.add(new User(rs.getLong("id")
                        , rs.getString("name")
                        , rs.getString("lastname")
                        , rs.getByte("age")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM USERS";
        System.out.println(sql);
        int result = executeUpdateSQL(sql);
        System.out.println(result);
    }
}
