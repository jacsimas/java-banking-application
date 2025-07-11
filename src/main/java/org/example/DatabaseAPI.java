package org.example;

import org.example.Abstraction.DbApiSource;

import java.sql.*;

public class DatabaseAPI implements DbApiSource {
/*
    private final String url = System.getenv("DB_URL");      // put into properties file
    private final String username = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");
*/
PreparedStatement connection;

    public DatabaseAPI() throws SQLException {
    }

    @Override
    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }
}

