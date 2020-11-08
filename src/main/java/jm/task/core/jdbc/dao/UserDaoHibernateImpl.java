package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.*;

public class UserDaoHibernateImpl implements UserDao {

    private static Session session;

    public UserDaoHibernateImpl() {
        session = Util.getSessionFactory().openSession();
    }

    private void executeUpdateSQL(String sql) {
        try {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println("ERROR in query execution");
            session.getTransaction().rollback();
            session.close();
            ex.printStackTrace();

        }

    }


    @Override
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
        executeUpdateSQL(sql);

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        System.out.println(sql);
        executeUpdateSQL(sql);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User c именем " + name + " добавлен в БД");
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session.beginTransaction();
            User user = session.get(User.class, id); // null if no row found напрямую из базы
            //User user = session.load(User.class, id); //exception if no row found через Proxy
            if (user != null) {
                session.delete(user);
                System.out.println("User " + user.getId() + " удален");
            }
            else {
                System.out.println("User " + id + " не существует");
            }
            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

        @Override
        public List<User> getAllUsers () {
            List<User> users = new ArrayList<>();
            try {
                session.beginTransaction();

                users = (List<User>) session.createQuery("From User").list();
                session.getTransaction().commit();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();

            }
            if (users.size() != 0) {
                return users;
            }
            else {
                return Collections.emptyList();
            }

        }

        @Override
        public void cleanUsersTable () {
            String sql = "DELETE FROM USERS";
            System.out.println(sql);
            executeUpdateSQL(sql);
        }

        @Override
        public void close () {
            try{
                session.close();
            }
            catch (Exception ex) {
                System.out.println("Ошибка при закрытии session");
                ex.printStackTrace();
            }
        }
    }
