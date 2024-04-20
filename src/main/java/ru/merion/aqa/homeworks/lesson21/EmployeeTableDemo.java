package ru.merion.aqa.homeworks.lesson21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectDemo {
    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        EmployeeTable table = new EmployeeTable(connection);

        ResultSet list = table.getAll();
        while (list.next()) {
            printInfo(list);
        }

        int empId = table.add("Merion", "Academy", "88005114974", 1381);

        ResultSet emp = table.getById(empId);
        emp.next();
        printInfo(emp);

        table.deleteById(empId);

        connection.close();
    }

    private static void printInfo(ResultSet list) throws SQLException {
        System.out.println(list.getInt("id") + " " + list.getString("first_name") + " " + list.getString("last_name") + " " + list.getString("company_id"));
    }
}
