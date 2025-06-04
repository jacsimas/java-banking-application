package org.example;

import org.example.CsvController.CsvController;
import org.example.Model.Customer;

import java.sql.*;

public class DriverManagerDB {

    private final String url = "jdbc:postgresql://localhost:5432/findb";      // put into properties file
    private final String username = "simon";
    private final String password = "password";

    public Connection connect() throws SQLException {
    return java.sql.DriverManager.getConnection(url, username, password);
    }

    public void connectToDb(){



        // 2) Get a connection (driver auto-loads if the JAR is on the class-path)
        try (Connection connection = java.sql.DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement("SELECT version()");
             ResultSet rs = ps.executeQuery()) {

            // 3) Work with the DB
            if (rs.next()) {
                System.out.println("Connected! Server says: " + rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();               // TODO: real error handling
        }
    }

    public void addCustomerRecord(String name, int money, String password) {
        String record = "INSERT INTO customers (nickname, money, password) " + "VALUES (?, ?, ?)";

        try (Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(record,
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.setInt(2, money);
            ps.setString(3, password);

            int rows = ps.executeUpdate();                          // returns 1
            System.out.println("Rows inserted: " + rows);

            // --- fetch the auto-generated primary key -------------
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);                      // ← your SERIAL / IDENTITY value
                    System.out.println("New customer id = " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
