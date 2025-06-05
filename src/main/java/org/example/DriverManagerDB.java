package org.example;

import org.example.Model.Customer;

import java.sql.*;

public class DriverManagerDB {

    private final String url = "jdbc:postgresql://localhost:5432/findb";      // put into properties file
    private final String username = "simon";
    private final String password = "password";

    public DriverManagerDB() throws SQLException {
    }

    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection(url, username, password);
    }

}

