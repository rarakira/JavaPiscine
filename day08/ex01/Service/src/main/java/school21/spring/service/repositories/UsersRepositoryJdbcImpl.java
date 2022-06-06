package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    HikariDataSource dataSource;

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            String query = "SELECT * FROM users WHERE id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next() == true) {
                user = new User(resultSet.getLong(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            String query = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                userList.add(new User(resultSet.getLong(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User entity) {
        String sqlQuery = "INSERT into users (id, email)" +
                " VALUES (" +
                entity.getId() + ", '" +
                entity.getEmail() + "');";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String sqlQuery = "UPDATE users " +
                "SET email='" + entity.getEmail() +
                "' WHERE id=" + entity.getId() + ";";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM users WHERE id = " + id + ";";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = Optional.ofNullable(null);
        String sqlQuery = "SELECT * FROM users WHERE email='" + email + "';";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next() == true) {
                user = Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
