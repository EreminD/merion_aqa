package ru.merion.aqa.lesson22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryJDBC implements UserRepository{
    private static final String sql = "INSERT INTO app_users(login, password, display_name, role) VALUES (?, ?, ?, 'admin')";

    private final Connection connection;

    public UserRepositoryJDBC(String connectionString, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
