package org.example;

import org.example.Model.Customer;

import java.sql.*;

public class DriverManagerDB {

    private final String url = System.getenv("DBurl");      // put into properties file
    private final String username = System.getenv("DBuser");
    private final String password = System.getenv("DBpassword");

    public DriverManagerDB() throws SQLException {
    }

    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection(url, username, password);
    }

}

