package ru.merion.aqa.lesson21;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatementDemo {

    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        String insertSql = "insert into company(\"name\") values (?), (?),(?),(?);";
        String selectSql = "select * from company order by id desc limit ?";

        PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, name);
        preparedStatement.executeUpdate();

        ResultSet keys = preparedStatement.getGeneratedKeys();
        while (keys.next()) {
            System.out.println(keys.getInt(1));
        }

        int limit = scanner.nextInt();

        PreparedStatement statementSelect = connection.prepareStatement(selectSql);
        statementSelect.setInt(1, limit);
        ResultSet resultSet = statementSelect.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("description"));
            System.out.println(resultSet.getString("is_active"));
        }

        connection.close();

//
//        String insertValues = "insert into company(\"name\", description, is_active) values(?, ?, ?);";
//
//        PreparedStatement ps = connection.prepareStatement(insertValues);
//        ps.setString(1, "Рога и копыта");
//        ps.setString(2, "Слоган");
//        ps.setBoolean(3, true);
    }
}
