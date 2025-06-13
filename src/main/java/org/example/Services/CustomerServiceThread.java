package org.example.Services;

import org.example.DBController;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class CustomerServiceThread extends Thread{

    CustomerServices cservice = new CustomerServices();
    DBController dbController = new DBController();

    String nameSenderCustomer;
    String nameReceiverCustomer;
    int amountToSend;

    public CustomerServiceThread(String threadname, String nameSenderCustomer, String nameReceiverCustomer, int amountToSend) throws SQLException {
        this.nameSenderCustomer = nameSenderCustomer;
        this.nameReceiverCustomer = nameReceiverCustomer;
        this.amountToSend = amountToSend;
    }

    // multiple threads access the same object in CustomerServices I think, should be guarded
    public void run() {
        int senderreturnedId = dbController.findCustomerByUsername(nameSenderCustomer);
        int receiverreturnedId = dbController.findCustomerByUsername(nameReceiverCustomer);

        boolean sendmoney;
        try {
            sendmoney = cservice.sendMoneyToSomeone(senderreturnedId, amountToSend);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println( " is running");
        try {
            sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (sendmoney){
            try {
                cservice.receiveMoney(receiverreturnedId, amountToSend);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                cservice.updateDbAfterTransfer(senderreturnedId, nameSenderCustomer, receiverreturnedId, nameReceiverCustomer, amountToSend);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
        // could later just change to method handling from CustomerServices

    public void start(){
        start();
    }
}
