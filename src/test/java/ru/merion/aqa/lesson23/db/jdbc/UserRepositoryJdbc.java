package ru.merion.aqa.lesson23.db.jdbc;

import ru.merion.aqa.lesson23.db.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryJdbc implements UserRepository {
    private static final String sql = "INSERT INTO app_users(login, password, display_name, role) VALUES (?, ?, ?, 'admin')";

    private final Connection connection;

    public UserRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(String login, String pass) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, login);
        ps.setString(2, pass);
        ps.setString(3, login);
        ps.executeUpdate();
    }
}
