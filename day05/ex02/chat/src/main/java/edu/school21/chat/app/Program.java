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

        User creator = new User(1L, "Noname", "111", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(1L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "The new message.", LocalDateTime.now());
        messagesRepository.save(message);
        System.out.println(message.getId());
        ds.close();
    }
}
