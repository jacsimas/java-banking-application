package org.example;

import org.example.Abstraction.DbApiSource;

import java.sql.*;

public class DatabaseAPI implements DbApiSource {

    public DatabaseAPI() throws SQLException {
    }

    @Override
    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection(System.getenv("DB_URL"),System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
    }
}

