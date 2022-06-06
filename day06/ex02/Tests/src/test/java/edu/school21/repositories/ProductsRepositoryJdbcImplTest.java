package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepository productsRepository;
    private EmbeddedDatabase dataSource;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Sourdough Baguette", 100),
            new Product(2L, "Multigrain Batard", 140),
            new Product(3L, "Brioche Buns", 80),
            new Product(4L, "Walnut Raisin Batard", 120),
            new Product(5L, "French Roll", 45),
            new Product(6L, "Challah", 155),
            new Product(7L, "Country Rye Tartine", 135)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(6L, "Challah", 155);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Brioche Bun", 40);
    final Product EXPECTED_SAVED_PRODUCT = new Product(8L, "Sourdough Tartine", 130);
    final Product EXPECTED_NOTSAVED_PRODUCT = new Product(1L, "Sourdough Tartine", 130);

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void findAllProductsTest () throws SQLException {
        Assertions.assertEquals(productsRepository.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    void findProductByIdTest () throws SQLException {
        Assertions.assertEquals(productsRepository.findById(6L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void updateProductTest () throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(3L).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void saveProductTest () throws SQLException {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(8L).get(), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    void saveProductExceptionTest () {
        Assertions.assertThrowsExactly(ProductsRepositoryJdbcImpl.NotSavedSubEntityException.class,
                () -> productsRepository.save(EXPECTED_NOTSAVED_PRODUCT));
    }

    @Test
    void deleteProductByIdTest () throws SQLException {
        productsRepository.delete(6L);
        Assertions.assertThrowsExactly(SQLException.class, () ->productsRepository.findById(6L).get());
    }

    @AfterEach
    void closeDs() {
        dataSource.shutdown();
    }
}
