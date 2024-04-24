package ru.merion.aqa.lesson22_hw;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTable {

    public static final String SQL_GET_ALL = "select * from employee";
    public static final String SQL_GET_BY_ID = "select * from employee where id = ?";
    public static final String SQL_DELETE_BY_ID = "delete from employee where id = ?";
    public static final String SQL_SET_STATUS = "update employee set is_active = ? where id = ?";
    public static final String SQL_INSERT = "insert into employee(first_name, last_name, phone, company_id) values(?,?,?,?)";
    public static final String SQL_COUNT_BY_COMP_ID = "select count(*) from employee where company_id = ?";
    private final Connection connection;

    public EmployeeTable(Connection connection) {
        this.connection = connection;
    }

    public int countByCompanyId(int compId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_COUNT_BY_COMP_ID);
        statement.setInt(1, compId);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        ResultSet rs = connection.createStatement().executeQuery(SQL_GET_ALL);
        while(rs.next()){
            Employee e = new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getBoolean("is_active"),
                    rs.getInt("company_id"),
                    rs.getString("email"),
                    rs.getString("phone")
            );
            list.add(e);
        }
        return list;
    }

    public Employee getById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return new Employee(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getBoolean("is_active"),
                rs.getInt("company_id"),
                rs.getString("email"),
                rs.getString("phone")
        );
    }

    public void deleteById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void setStatus(int empId, boolean isActive) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SET_STATUS);
        statement.setBoolean(1, isActive);
        statement.setInt(2, empId);
        statement.executeUpdate();
    }

    public int add(String firstName, String lastName, String phone, int compId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setString(3, phone);
        statement.setInt(4, compId);
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }



}
