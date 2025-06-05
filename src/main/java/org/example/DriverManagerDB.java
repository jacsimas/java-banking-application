package org.example;

import org.example.Model.Customer;

import java.sql.*;

public class DriverManagerDB {

    Connection connection = connect();
    private final String url = "jdbc:postgresql://localhost:5432/findb";      // put into properties file
    private final String username = "simon";
    private final String password = "password";

    public DriverManagerDB() throws SQLException {
    }

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
        String sql = "INSERT INTO customers (nickname, money, password) " + "VALUES (?, ?, ?)";

        try (
                PreparedStatement ps = connection.prepareStatement(sql,
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

    public Customer returnRecord(int customerId) throws SQLException {

        int id = 0;
        String name = null;
        int money = 0;
        String cusPassword = null;
        String sql = "SELECT * FROM customers WHERE id = ?";
                 // + customerId + " (id, nickname, money, password) " + "VALUES (?, ?, ?)";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, 3);

            try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                id = rs.getInt("id");           // error here probably
                name = rs.getString("nickname");
                money = rs.getInt("money");
                cusPassword = rs.getString("password");
            }else {
                System.out.println("No row found for id = 42");
            }
            }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return new Customer(id, name, money, cusPassword);
        }

    public int updateCustomerFunds(int customerId, int newAmount) {

        String sql = "UPDATE customers SET money = ? WHERE id = ? ";
        int updated = 0;
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, newAmount);
            ps.setInt(2,customerId);

            updated = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
         }
        return updated;
    }
}

