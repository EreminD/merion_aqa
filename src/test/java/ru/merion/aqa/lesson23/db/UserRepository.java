package ru.merion.aqa.lesson23.db;

import java.sql.SQLException;

public interface UserRepository {
    void addUser(String login, String pass) throws SQLException;
}
