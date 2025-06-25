package org.example.Services;

import org.example.Controller.TransferCalculator;
import org.example.Currencies.CurrencyAPI;
import org.example.DBController;
import org.example.Model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class CustomerServices {

    DBController dbcontroller = new DBController();
    TransferCalculator transactions = new TransferCalculator();


    public CustomerServices() throws SQLException {
    }

    public void depositMoney(String customerName, int depositAmount) throws IOException, SQLException {

        int returnedId = dbcontroller.findCustomerByUsername(customerName);
        Customer customer = dbcontroller.returnRecord(returnedId);
        int oldAmount = customer.moneyInCents();
        int newAmount = transactions.receiveMoneyTransaction(oldAmount, depositAmount);
        boolean fundsupdated = dbcontroller.updateCustomerFunds(returnedId, newAmount);
        if (fundsupdated){
            System.out.println(customerName + " funds were updated!");
        }
    }

    public boolean makeTransfer(String nameSenderCustomer, String nameReceiverCustomer, int amountToSend) throws SQLException, IOException {
        int senderreturnedId = dbcontroller.findCustomerByUsername(nameSenderCustomer);
        int receiverreturnedId = dbcontroller.findCustomerByUsername(nameReceiverCustomer);

        boolean sendmoney = sendMoneyToSomeone(senderreturnedId, amountToSend);
        if (sendmoney){
            receiveMoney(receiverreturnedId, amountToSend);
            updateDbAfterTransfer(senderreturnedId, nameSenderCustomer, receiverreturnedId, nameReceiverCustomer, amountToSend);
            return true;
        }
        return false;
    }

 // TODO: what if funds insufficient? add error handlers

    public boolean sendMoneyToSomeone(int senderreturnedId, int amountToSend) throws IOException, SQLException {

        Customer sendercustomer = dbcontroller.returnRecord(senderreturnedId);

        int senderMoney = sendercustomer.moneyInCents();
        boolean hasenough = transactions.checkIfCustomerHasFundsToMakeTransaction(senderMoney, amountToSend);
        if (hasenough) {
            int newsenderamount = transactions.sendMoneyTransaction(senderMoney, amountToSend);
            dbcontroller.updateCustomerFunds(senderreturnedId, newsenderamount);
            return true;
        }
        return false;
    }

    public void receiveMoney(int receiverreturnedId, int amountToSend) throws SQLException {

        Customer receivercustomer = dbcontroller.returnRecord(receiverreturnedId);
        int receiverMoney = receivercustomer.moneyInCents();
        int newreceiveramount = transactions.receiveMoneyTransaction(receiverMoney, amountToSend);

        dbcontroller.updateCustomerFunds(receiverreturnedId, newreceiveramount);

    }

    public void updateDbAfterTransfer(int senderreturnedId, String nameSenderCustomer, int receiverreturnedId, String nameReceiverCustomer, int amountToSend) throws SQLException {

        dbcontroller.insertSenderReceiverIntoTransferTotals(senderreturnedId, receiverreturnedId, amountToSend);
        dbcontroller.insertReceiverSenderIntoTransferTotals(senderreturnedId, receiverreturnedId, amountToSend);
        dbcontroller.createTransactionAudit(senderreturnedId, nameSenderCustomer, amountToSend, receiverreturnedId, nameReceiverCustomer);

        boolean isfriends = dbcontroller.checkIfHasFriend(senderreturnedId, receiverreturnedId);
        if (isfriends){
            dbcontroller.insertIntoTransfers(senderreturnedId, receiverreturnedId, amountToSend);
        }
    }

    public void convertMyFundsToAllCurrencies(String nameCustomer) throws IOException, SQLException {
        // take customer's funds from DB; by name, find id, then take funds from customer object
        // take currencies HashMap
        // initiate a new hashmap, iterate through currencies map and multiply money value with each
        // save new values in the new hashmap, return it and display it

        CurrencyAPI currencyapi = new CurrencyAPI();
        HashMap<String, Double> currencies = currencyapi.getCurrencies();
        int customerId = dbcontroller.findCustomerByUsername(nameCustomer);
        Customer returnedCustomerObj = dbcontroller.returnRecord(customerId);
        int customerFunds = returnedCustomerObj.moneyInCents();
        System.out.println(customerFunds + "\n");
        for (Object i : currencies.keySet()) {
            double value = currencies.get(i);
            System.out.println(i + " rate: " + value + " " + value * customerFunds);
        }
    }

    public void exchangeToOtherCurrency(){
        //TODO: create a new column in database if needed and do an exchange operation for selected amount.
    }
/*
    private void insufficientFundsMessage() {
        System.out.println("You don't have the amount selected to continue with this transaction!");
        // should ask to retype the amount again on UI
    }

    public boolean receiveMoneyFromSomeone(int receiverFundsAfterTransaction, Customer receiverCustomer) throws IOException {

    }

    public void createNewCustomer(String customerName, int firstDeposit) throws IOException {

    }

    public boolean sendFriendRequest(Customer senderCustomer, Customer receiverCustomer) throws IOException {

    }


    public boolean acceptFriendRequest(Customer receiverCustomer, Customer senderCustomer) throws IOException {

    }
*/

}
// receiving could be a separate function, if something breaks, there should be some intermediary to hold the
// transaction, for example, money is already being sent, but due to connection loss receiver didn't receive,
// so money is put on hold somewhere? Or is it actually safer just to do in one go with one function, since
// it will already gonna hold both customers objects ??