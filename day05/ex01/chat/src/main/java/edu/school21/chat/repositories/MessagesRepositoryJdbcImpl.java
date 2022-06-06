package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        User user = new User(1, "rara", "000", null, null);
        Chatroom chatroom = new Chatroom(1, "Secret room", null, null);
        optionalMessage = Optional.of(new Message(resultSet.getLong(1),
                user, chatroom, resultSet.getString("message"),
                LocalDateTime.of(2022, 2, 19, 14, 5, 11)));
        connection.close();

        return optionalMessage;
    }
}
