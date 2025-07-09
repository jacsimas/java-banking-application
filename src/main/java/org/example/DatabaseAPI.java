package org.example;

import java.sql.*;

public class DatabaseAPI {

    private final String url = System.getenv("DB_URL");      // put into properties file
    private final String username = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");

    public DatabaseAPI() throws SQLException {
    }

    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection(url, username, password);
    }

}

