package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/bd1";   //useSSL=false" + "&serverTimezone=UTC"
    private static final String PASSWORD = "12345678";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

}
