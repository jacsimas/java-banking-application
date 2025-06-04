package org.example;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String name = "john";
        int money = 5000;
        String password  = "pass1";
        DriverManagerDB drivermanager = new DriverManagerDB();

        drivermanager.connectToDb();
        drivermanager.addCustomerRecord(name, money, password);
    }
}

/*

        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/findb",
                            "simon", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
--
  ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        while (resultSet.next())
        {
            String columnValue = resultSet.getString("nickname");
            System.out.println("Column Value: " + columnValue);
        }

        // Perform desired database operations

        // Close the connection
        resultSet.close();
        statement.close();
        connection.close();
 */