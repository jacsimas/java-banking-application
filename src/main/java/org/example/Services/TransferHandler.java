package org.example.Services;

import org.example.DBController;

import java.io.IOException;
import java.sql.SQLException;

public class TransferHandler {
    // CAN BE AN OBJECT RECEIVED FROM THE FRONTEND

    DBController dbcontroller;
    CustomerServices customerservices = new CustomerServices();

    String nameSenderCustomer = null;
    String nameReceiverCustomer = null;
    int amountToSend = 0;

    public TransferHandler(String nameSenderCustomer, String nameReceiverCustomer, int amountToSend) throws SQLException, IOException {
        this.nameSenderCustomer = nameSenderCustomer;
        this.nameReceiverCustomer = nameReceiverCustomer;
        this.amountToSend = amountToSend;
    }

}
