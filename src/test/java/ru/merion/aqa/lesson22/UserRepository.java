package ru.merion.aqa.lesson22;

import java.sql.SQLException;

public interface UserRepository {

    void addUser(String login, String pass) throws SQLException;
}
