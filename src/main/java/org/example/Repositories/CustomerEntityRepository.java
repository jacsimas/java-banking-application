package org.example.Repositories;


import org.example.Model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CustomerEntityRepository {

    final Logger logger = LoggerFactory.getLogger(CustomerEntityRepository.class);

    private final Connection connection;

    public CustomerEntityRepository(Connection connection) throws SQLException {
        this.connection = connection;
        System.out.println("psql connection object: " + this.connection);
    }

    public void addCustomerRecord(String name, int money, String password) throws SQLException {
        String sql = "INSERT INTO customers (nickname, money, password) VALUES (?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);{

            ps.setString(1, name);
            ps.setInt(2, money);
            ps.setString(3, password);

            int rows = ps.executeUpdate();                          // returns 1
            logger.trace("Number of rows inserted: {}", rows);
            if (rows == 0){
                logger.error("Error");
            }
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);                      // ← your SERIAL / IDENTITY value
                    logger.info("New customer id = {}", id);
                }
            }
        }
    }

    public Customer returnRecord(int customerId) throws SQLException {
        String sql = "SELECT id, nickname, money, password FROM customers WHERE id = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Customer(rs.getInt("id"), rs.getString("nickname"), rs.getInt("money"), rs.getString("password"));
            }
            //TODO: make this code with error logging
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int findCustomerByUsername(String name) throws SQLException {
        int id = 0;
        String sql = "SELECT id FROM customers WHERE nickname = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            logger.error("error: {}", e);
        }
        return id;
    }


    public boolean updateCustomerFunds(int customerId, int newAmount) throws SQLException {  //void

        String sql = "UPDATE customers SET money = ? WHERE id = ? ";
        try ( //
              PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, newAmount);
            ps.setInt(2,customerId);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkIfHasFriend(int senderId, int receiverId) throws SQLException {

        String sql = "SELECT FROM friendships WHERE customer_id = ? AND friend_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setLong(1, senderId);
        ps.setLong(2, receiverId);

        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();

        if (!result) {
            ps.setLong(1, receiverId);
            ps.setLong(2, senderId);

            rs = ps.executeQuery();
            return rs.next();
        }
        return true;
    }
}

