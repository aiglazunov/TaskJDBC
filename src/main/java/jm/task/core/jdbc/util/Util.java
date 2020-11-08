package jm.task.core.jdbc.util;

import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.*;
import org.hibernate.service.*;

import java.sql.*;
import java.util.*;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/jm?serverTimezone=UTC";
    private static final String USERNAME = "jm";
    private static final String PASSWORD = "1Qaz2Wsx";

    private static Connection connection = null;

    private static SessionFactory sessionFactory = null;


    public  static SessionFactory getSessionFactory() {
        try {
            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Configuration conf = new Configuration()
                    //.addClass(jm.task.core.jdbc.model.User.class)
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "none")
                    .setProperty("hibernate.connection.autocommit","false")
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USERNAME)
                    .setProperty("hibernate.connection.password", PASSWORD);
                    //.configure();

//            sessionFactory = conf.buildSessionFactory();

            StandardServiceRegistryBuilder  serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
            sessionFactory = conf.buildSessionFactory(serviceRegistry.build());
            System.out.println("Connection OK!");


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Connection Error!");

        }
        return sessionFactory;
    }




    public static Connection getConnection() {
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connection Ok!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Connection Error!");
            ex.getStackTrace();
        }


        return connection;
    }


}
