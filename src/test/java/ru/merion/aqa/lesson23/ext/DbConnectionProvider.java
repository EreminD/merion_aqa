package ru.merion.aqa.lesson23.ext;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectionProvider implements BeforeAllCallback, AfterAllCallback {
    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";
    private Connection connection;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        this.connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("db_connection", connection);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (connection != null){
            connection.close();
        }
    }
}
