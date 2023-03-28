package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        Connection connection1 = getConnection();
        Statement statement = null;
        try  { statement = connection1.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User " +
                    "(Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection1 != null) {
                connection1.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection2 = getConnection();
        Statement statement = null;
        try { statement = connection2.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS User");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection2 != null) {
                connection2.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection3 = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO User (name, last_name, age) VALUES (?, ?, ?)";
        try {preparedStatement = connection3.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection3 != null) {
                connection3.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection4 = getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "Delete from User where Id=?";

        try {preparedStatement = connection4.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection4 != null) {
                connection4.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection5 = getConnection();

        try (ResultSet resultSet = connection5.createStatement().executeQuery("SELECT * FROM User")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection5 != null) {
                connection5.close();
            }
        }
        return users;
    }

    public void cleanUsersTable () throws SQLException {
        Connection connection6 = getConnection();
        Statement statement = null;
        try { statement = connection6.createStatement();
            statement.executeUpdate("TRUNCATE TABLE User");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection6 != null) {
                connection6.close();
            }
        }
    }

}