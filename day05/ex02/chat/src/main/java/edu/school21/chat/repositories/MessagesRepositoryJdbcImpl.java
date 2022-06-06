package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Optional<Message> optionalMessage;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM chat.messages WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        User user = new User(1L, "rara", "000", null, null);
        Chatroom chatroom = new Chatroom(1L, "Secret room", null, null);
        optionalMessage = Optional.of(new Message(resultSet.getLong(1),
                user, chatroom, resultSet.getString("message"),
                LocalDateTime.of(2022, 2, 19, 14, 5, 11)));
        connection.close();

        return optionalMessage;
    }

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        String sqlQuery = "INSERT into chat.messages (author, room, message, time)" +
                " VALUES (" +
                message.getAuthor().getId() + ", " +
                message.getRoom().getId() + ", " +
                "'" + message.getText() + "'" + ", " +
                "'" + message.getDateAndTime() + "'" +
                ");";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.execute();

            ResultSet key = statement.getGeneratedKeys();
            key.next();
            message.setId(key.getLong(1));
        } catch (SQLException e) {
            throw new NotSavedSubEntityException();
        }
        return true;
    }
}
