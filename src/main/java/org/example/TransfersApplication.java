package org.example;

import org.example.Abstraction.DbApiSource;
import org.example.Currencies.CurrencyAPI;
import org.example.Repositories.CustomerEntityRepository;
import org.example.Repositories.TransfersRepository;
import org.example.Services.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TransfersApplication {

    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(TransfersApplication.class, args);


        DbApiSource databaseApi = new DatabaseAPI();

        Connection connection = databaseApi.connect();

        CustomerEntityRepository customerentityrepository = new CustomerEntityRepository(connection);
        TransfersRepository transfersrepository = new TransfersRepository(connection);
        CustomerServices customerServices = new CustomerServices(customerentityrepository, transfersrepository);

        // I tested if each repository class and services classes work after implementing Dependency Inversion
       System.out.println("transactions_audit data TEST: " + transfersrepository.returnTransactionAudit(15));
       System.out.println("returnRecord customer data TEST: " + customerentityrepository.returnRecord(2));
       customerServices.depositMoney("derry", 200);

        final Logger log = LoggerFactory.getLogger(TransfersApplication.class);

        CurrencyAPI currencyapi = new CurrencyAPI();
        HashMap<String, Double> currencies = currencyapi.getCurrencies();

        for (Object i : currencies.keySet()) {
            System.out.println(i + " : " + currencies.get(i));
        }

        CustomerServices customerserviceobj1 = new CustomerServices(customerentityrepository, transfersrepository);
        CustomerServices customerserviceobj2 = new CustomerServices(customerentityrepository, transfersrepository);

        customerserviceobj1.makeTransfer("derry", "jack", 20);

        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() -> {
            try {
                customerserviceobj1.makeTransfer("derry", "jack", 10);
            } catch (SQLException | IOException e) {
                log.error("Operation wasn't successful: {}", e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });
        pool.submit(() -> {
            try {
                customerserviceobj2.makeTransfer("jack", "jennifer", 10);
            } catch (SQLException | IOException e) {
                log.error("Operation wasn't successful: {}", e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });

        pool.shutdown();



    }

}


//        may change to such type of structure later on:
//        Thread t = new Thread(new Worker(new Calculator()));
//        t.start();


        // f.e. put:     "johnny", "jack", 3000
        //               "derry", "jennifer", 3000


