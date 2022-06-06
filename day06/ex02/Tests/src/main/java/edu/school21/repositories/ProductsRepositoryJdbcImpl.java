package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource dataSource;
    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM product;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            productList.add(new Product(resultSet.getLong(1),
                                        resultSet.getString(2),
                                        resultSet.getInt(3)));
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String sqlQuery = "SELECT * FROM product WHERE identifier = " + id + ";";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        resultSet.next();
        Optional<Product> optionalProduct = Optional.of(new Product(resultSet.getLong(1),
                                                                    resultSet.getString(2),
                                                                    resultSet.getInt(3)));
        statement.close();
        connection.close();
        return optionalProduct;
    }

    @Override
    public void update(Product product) throws SQLException {
        String sqlQuery = "UPDATE product " +
                "SET name='" + product.getName() +
                "', price=" + product.getPrice() +
                " WHERE identifier=" + product.getId() + ";";
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(sqlQuery);
        statement.close();
        connection.close();
    }

    @Override
    public void save(Product product) {
        String sqlQuery = "INSERT into product (identifier, name, price)" +
                " VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', " +
                product.getPrice() +  ");";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String query = "DELETE FROM product WHERE identifier = " + id + ";";
        statement.executeQuery(query);
        statement.close();
        connection.close();
    }

    public class NotSavedSubEntityException extends RuntimeException {
        @Override
        public void printStackTrace() {
            System.out.println("Can't save product");
        }
    }
}
