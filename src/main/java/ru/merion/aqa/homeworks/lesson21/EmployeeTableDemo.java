package ru.merion.aqa.homeworks.lesson21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class EmployeeTableDemo {
    public static final String CONNECTION_STRING = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
    public static final String USERNAME = "x_clients_user";
    public static final String PASSWORD = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

        EmployeeTable table = new EmployeeTable(connection);

        List<Employee> list = table.getAll();
        list.forEach(System.out::println);

        int empId = table.add("Merion", "Academy", "88005114974", 1381);

        table.setStatus(empId, false);

        Employee e = table.getById(empId);
        System.out.println(e);

        table.deleteById(empId);

        connection.close();
    }
}
