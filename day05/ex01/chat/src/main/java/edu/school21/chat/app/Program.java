package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds = new HikariDataSource(config);
    }

    public static void main(String[] args) throws SQLException {
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(ds);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.print("-> ");
        System.out.println(repository.findById(scanner.nextLong()).get());
        ds.close();
    }
}
