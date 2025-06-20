package org.example;

import org.example.Model.Customer;
import org.example.Model.TransactionAudit;
import org.example.Services.CustomerServiceThread;
import org.example.Services.CustomerServices;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

        // TODO:
//        DBController dbcontrol = new DBController();
//
//        ThreadTest R1 = new ThreadTest( "Thread-1");
//        R1.start();
//
//        ThreadTest R2 = new ThreadTest( "Thread-2");
//        R2.start();

        CustomerServices customerserviceobj1 = new CustomerServices();
        CustomerServices customerserviceobj2 = new CustomerServices();

        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() -> {
            try {
                customerserviceobj1.makeTransfer("johnny", "jack", 3000);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pool.submit(() -> {
            try {
                customerserviceobj2.makeTransfer("derry", "jennifer", 3000);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pool.shutdown();


//        may change to such type of structure later on:
//        Thread t = new Thread(new Worker(new Calculator()));
//        t.start();


        // f.e. put:     "johnny", "jack", 3000
        //               "derry", "jennifer", 3000
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