package org.example;

import org.example.Model.Customer;
import org.example.Model.TransactionAudit;
import org.example.Services.CustomerServiceThread;
import org.example.Services.CustomerServices;
import org.example.Services.ThreadTest;
import org.example.Services.TransferHandler;

import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

//        DBController dbcontrol = new DBController();
//
//        ThreadTest R1 = new ThreadTest( "Thread-1");
//        R1.start();
//
//        ThreadTest R2 = new ThreadTest( "Thread-2");
//        R2.start();


        CustomerServiceThread thread1 = new CustomerServiceThread("transfer 1", "johnny", "jack", 3000);
        CustomerServiceThread thread2 = new CustomerServiceThread("transfer 2", "derry", "jennifer", 3000);

        thread1.start();
        thread2.start();


//        may change to such type of structure later on:
//        Thread t = new Thread(new Worker(new Calculator()));
//        t.start();

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