package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds = new HikariDataSource(config);
    }

    public static void main(String[] args) throws SQLException {
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);

        Optional<Message> messageOptional = messagesRepository.findById(5L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setDateAndTime(null);
            messagesRepository.update(message);
        }
        ds.close();
    }
}
