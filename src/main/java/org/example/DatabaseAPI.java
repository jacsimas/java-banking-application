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
  /*  final String url = System.getenv("DB_URL");      // put into properties file
    final String username = System.getenv("DB_USER");
    final String password = System.getenv("DB_PASSWORD");
*/
  /*  private final String url = "jdbc:postgresql://localhost:5432/findb";
    private final String username = "simon";
    private final String password = "password";
*/
    @Override
    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/findb", "simon", "password");
    }


}

