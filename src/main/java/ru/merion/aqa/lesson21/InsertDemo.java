package ru.merion.aqa.lesson21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertDemo {

    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";


    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        String insert = "insert into company(\"name\") values('" + name + "');";
        connection.createStatement().executeUpdate(insert);

        int limit = scanner.nextInt();
        ResultSet resultSet = connection.createStatement().executeQuery("select * from company order by id desc limit " + limit);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("description"));
            System.out.println(resultSet.getString("is_active"));
        }

        connection.close();


        // SQL Injection --> abc'); delete from company; --
    }
}

