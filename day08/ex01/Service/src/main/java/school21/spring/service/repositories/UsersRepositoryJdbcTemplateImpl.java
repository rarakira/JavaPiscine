package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    public JdbcTemplate jdbcDataSource;

    public UsersRepositoryJdbcTemplateImpl(DriverManagerDataSource ds) {
        this.jdbcDataSource = new JdbcTemplate(ds);
    }

    @Override
    public User findById(Long id) {
        String sqlQuery = "SELECT * FROM users WHERE id=" + id + ";";
        return jdbcDataSource.query(sqlQuery, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        String sqlQuery = "SELECT * FROM users;";
        return jdbcDataSource.query(sqlQuery, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcDataSource.update("INSERT INTO users VALUES (?, ?)", entity.getId(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcDataSource.update("UPDATE users SET email='" + entity.getEmail() + "' WHERE id=" + entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcDataSource.update("DELETE FROM users WHERE id = " + id + ";");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sqlQuery = "SELECT * FROM users WHERE email = '" + email + "';";
        return Optional.ofNullable(jdbcDataSource.query(sqlQuery, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null));
    }
}
