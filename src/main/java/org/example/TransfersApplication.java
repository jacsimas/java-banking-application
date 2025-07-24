package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class TransfersApplication {

    private static final Logger log = LoggerFactory.getLogger(TransfersApplication.class);

    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(TransfersApplication.class, args);

        final Logger log = LoggerFactory.getLogger(TransfersApplication.class);

   //     TransfersRepository transfersRepository = new TransfersRepository();
      //  TransactionAuditController transactionAuditController = new TransactionAuditController(transfersRepository);



// jdbc:postgresql://172.31.224.1:5432/findb
/*

        CurrencyApiSource currencyapi = new CurrencyAPI();
        URL url = currencyapi.connectCurrencyApi();
        CurrencyEntity currencyEntity = new CurrencyEntity(url);

        HashMap<String, Double> currencies = currencyEntity.getCurrencies();
        for (Object i : currencies.keySet()) {
            System.out.println(i + " : " + currencies.get(i));
        }


        TransferCalculator transactions = new TransferCalculator();
        DbApiSource databaseApi = new DatabaseAPI();

        Connection connection = databaseApi.connect();

        CustomerEntityRepository customerentityrepository = new CustomerEntityRepository(connection);
        TransfersRepository transfersrepository = new TransfersRepository(connection);
        CustomerServices customerServices = new CustomerServices(customerentityrepository, transfersrepository, transactions, currencyEntity);


        // I tested if each repository class and services classes work after implementing Dependency Inversion
       System.out.println("transactions_audit data TEST: " + transfersrepository.returnTransactionAudit(15));
       System.out.println("returnRecord customer data TEST: " + customerentityrepository.returnRecord(2));
       customerServices.depositMoney("derry", 200);



        CustomerServices customerserviceobj1 = new CustomerServices(customerentityrepository, transfersrepository, transactions, currencyEntity);
        CustomerServices customerserviceobj2 = new CustomerServices(customerentityrepository, transfersrepository, transactions, currencyEntity);

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


 */

    }
}

