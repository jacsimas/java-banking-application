package org.example.Controller;

import org.example.CsvController.CsvController;
import org.example.Model.Customer;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class CustomerActionsController{

    CsvController csvcontroller = new CsvController();
    TransactionController transactioncontroller = new TransactionController();

    public int putMoneyIntoAccount(int actualCustomerId, int depositAmount) throws IOException {

        Customer meCustomer = csvcontroller.returnCustomerFromCsvFile(actualCustomerId);
        int meCustomerAmount = meCustomer.getMoneyInCents();
        meCustomerAmount = meCustomerAmount + depositAmount;
        meCustomer.setMoneyInCents(meCustomerAmount);

        csvcontroller.writeCustomerAmountBackToNewCsvFile(meCustomer);
        csvcontroller.deleteOldFileAndRenameNewOne();
        return meCustomerAmount;
    }


    public boolean sendMoneyToSomeone(int idSenderCustomer, int idReceiverCustomer, int amountToSend) throws IOException {

       Customer senderCustomer =  csvcontroller.returnCustomerFromCsvFile(idSenderCustomer);
       Customer receiverCustomer = csvcontroller.returnCustomerFromCsvFile(idReceiverCustomer);
       int senderFunds = senderCustomer.getMoneyInCents();
       int receiverFunds = receiverCustomer.getMoneyInCents();
       boolean hasFunds =  transactioncontroller.checkIfCustomerHasFundsToMakeTransaction(senderFunds, amountToSend);

       if (!hasFunds){
        insufficientFundsMessage();
        return false;
       }

       int senderFundsAfterTransaction = transactioncontroller.sendMoneyTransaction(senderFunds, amountToSend);
       int receiverFundsAfterTransaction = transactioncontroller.receiveMoneyTransaction(receiverFunds, amountToSend);

       if (senderFundsAfterTransaction < senderFunds && receiverFundsAfterTransaction > receiverFunds) {
           senderCustomer.setMoneyInCents(senderFundsAfterTransaction);

           csvcontroller.writeCustomerAmountBackToNewCsvFile(senderCustomer);
           csvcontroller.deleteOldFileAndRenameNewOne();

           boolean received = receiveMoneyFromSomeone(receiverFundsAfterTransaction, receiverCustomer);
           if (received) {
               csvcontroller.addTransactionDataToTransactionRecords(senderCustomer, receiverCustomer, amountToSend);
               csvcontroller.saveTransactionToCustomerCsv(senderCustomer, receiverCustomer, amountToSend);
               return true;
           }
           }
       return false;
    }

    private void insufficientFundsMessage() {
        System.out.println("You don't have the amount selected to continue with this transaction!");
        // should ask to retype the amount again on UI
    }

    public boolean receiveMoneyFromSomeone(int receiverFundsAfterTransaction, Customer receiverCustomer) throws IOException {

        receiverCustomer.setMoneyInCents(receiverFundsAfterTransaction);

        csvcontroller.writeCustomerAmountBackToNewCsvFile(receiverCustomer);
        csvcontroller.deleteOldFileAndRenameNewOne();

        return true;
    }

    public void createNewCustomer(String customerName, int firstDeposit) throws IOException {

        int id = csvcontroller.readWriteId();

        Customer newcustomer = new Customer(id, customerName, firstDeposit);
        csvcontroller.writeCustomersToCsv(newcustomer);
    }

    public boolean sendFriendRequest(Customer senderCustomer, Customer receiverCustomer) throws IOException {

        String message = "You received a friend request";
        boolean messageSent = csvcontroller.createUpdateMessageBox(senderCustomer, receiverCustomer, message);

        if (messageSent){
            return true;
        } else return false;
    }


    public boolean acceptFriendRequest(Customer receiverCustomer, Customer senderCustomer) throws IOException {

        String receiverName = receiverCustomer.getUser();
        String message = receiverName + " accepted your friend request!";
        boolean messageSent = csvcontroller.createUpdateMessageBox(receiverCustomer, senderCustomer, message);
        if (messageSent){
            boolean addedToFriends = csvcontroller.createUpdateFriendsList(senderCustomer, receiverCustomer);
            if (addedToFriends) {
                return true;
            } else return false;
        } else return false;
    }

}
// receiving could be a separate function, if something breaks, there should be some intermediary to hold the
// transaction, for example, money is already being sent, but due to connection loss receiver didn't receive,
// so money is put on hold somewhere? Or is it actually safer just to do in one go with one function, since
// it will already gonna hold both customers objects ??