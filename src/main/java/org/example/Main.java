package org.example;

import org.example.Model.Customer;
import org.example.Model.TransactionAudit;
import org.example.Services.CustomerServices;
import org.example.Services.ThreadTest;
import org.example.Services.TransferHandler;

import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

//        DBController dbcontrol = new DBController();
//
//        ThreadTest R1 = new ThreadTest( "Thread-1");
//        R1.start();
//
//        ThreadTest R2 = new ThreadTest( "Thread-2");
//        R2.start();

        CustomerServices cservices = new CustomerServices();

        cservices.makeTransfer("derry", "jennifer", 2000);
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