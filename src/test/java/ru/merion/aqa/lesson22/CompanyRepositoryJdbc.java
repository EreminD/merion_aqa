package ru.merion.aqa.lesson22;

import java.sql.*;

public class CompanyRepositoryJdbc implements CompanyRepository {

    private static final String GET_COMPANY_BY_ID = "SELECT * FROM company WHERE id = ?";
    private static final String COUNT_COMPANIES = "SELECT count(*) FROM company WHERE deleted_at IS NULL";
    private static final String COUNT_ACTIVE_COMPANIES = "SELECT count(*) FROM company WHERE deleted_at IS NULL and is_active = ?";
    private static final String CREATE_COMPANY = "INSERT INTO company(\"name\", description) VALUES (?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM company WHERE ID = ?";
    private final Connection connection;

    public CompanyRepositoryJdbc(String connectionString, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public ResultSet getCompanyById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_COMPANY_BY_ID);
        ps.setInt(1, id);
        System.out.println("SQL: " + ps);
        return ps.executeQuery();
    }

    public int createCompany(String name, String description) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, description);
        System.out.println("SQL: " + ps);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public int countCompanies() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(COUNT_COMPANIES);
        rs.next();
        return rs.getInt(1);
    }

    public int countCompanies(boolean isActive) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(COUNT_ACTIVE_COMPANIES);
        ps.setBoolean(1, isActive);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        System.out.println("rowsAffected = " + rowsAffected);
    }
}
