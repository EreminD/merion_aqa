package ru.merion.aqa.lesson22;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CompanyRepository {

    ResultSet getCompanyById(int id) throws SQLException;

    int createCompany(String name, String description) throws SQLException;

    int countCompanies() throws SQLException;

    int countCompanies(boolean isActive) throws SQLException;

    void deleteById(int id) throws SQLException;

    void closeConnection() throws SQLException;
}
