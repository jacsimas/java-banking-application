package org.example.Services;

import org.example.Controller.TransferCalculator;
import org.example.DBController;
import org.example.Model.Customer;

import java.io.IOException;
import java.sql.SQLException;


public class CustomerServices {

    DBController dbcontroller = new DBController();
    TransferCalculator transactions = new TransferCalculator();


    public CustomerServices() throws SQLException {
    }

    public void depositMoney(String customerName, int depositAmount) throws IOException, SQLException {

        int returnedId = dbcontroller.findCustomerByUsername(customerName);
        Customer customer = dbcontroller.returnRecord(returnedId);
        int oldAmount = customer.getMoneyInCents();
        int newAmount = transactions.receiveMoneyTransaction(oldAmount, depositAmount);
        boolean fundsupdated = dbcontroller.updateCustomerFunds(returnedId, newAmount);
        if (fundsupdated){
            System.out.println(customerName + " funds were updated!");
        }
    }

//    public boolean makeTransfer(String nameSenderCustomer, String nameReceiverCustomer, int amountToSend) throws SQLException, IOException {
//        int senderreturnedId = dbcontroller.findCustomerByUsername(nameSenderCustomer);
//        int receiverreturnedId = dbcontroller.findCustomerByUsername(nameReceiverCustomer);
//
//        boolean sendmoney = sendMoneyToSomeone(senderreturnedId, amountToSend);
//        if (sendmoney){
//            receiveMoney(receiverreturnedId, amountToSend);
//            updateDbAfterTransfer(senderreturnedId, nameSenderCustomer, receiverreturnedId, nameReceiverCustomer, amountToSend);
//            return true;
//        }
//        return false;
//    }

 // what if funds insufficient? add error handlers
    public boolean sendMoneyToSomeone(int senderreturnedId, int amountToSend) throws IOException, SQLException {

        Customer sendercustomer = dbcontroller.returnRecord(senderreturnedId);

        int senderMoney = sendercustomer.getMoneyInCents();
        boolean hasenough = transactions.checkIfCustomerHasFundsToMakeTransaction(senderMoney, amountToSend);
        if (hasenough) {
            int newsenderamount = transactions.sendMoneyTransaction(senderMoney, amountToSend);
            dbcontroller.updateCustomerFunds(senderreturnedId, newsenderamount);
            return true;
        }
        return false;
    }  // add isMoneyReceived method, splitting responsibilities and securing the transfers

    public void receiveMoney(int receiverreturnedId, int amountToSend) throws SQLException {

        Customer receivercustomer = dbcontroller.returnRecord(receiverreturnedId);
        int receiverMoney = receivercustomer.getMoneyInCents();
        int newreceiveramount = transactions.receiveMoneyTransaction(receiverMoney, amountToSend);

        dbcontroller.updateCustomerFunds(receiverreturnedId, newreceiveramount);

    }

    public void updateDbAfterTransfer(int senderreturnedId, String nameSenderCustomer, int receiverreturnedId, String nameReceiverCustomer, int amountToSend) throws SQLException {

        dbcontroller.insertIntoTransferTotals1(senderreturnedId, receiverreturnedId, amountToSend);
        dbcontroller.insertIntoTransferTotals2(senderreturnedId, receiverreturnedId, amountToSend);
        dbcontroller.createTransactionAudit(senderreturnedId, nameSenderCustomer, amountToSend, receiverreturnedId, nameReceiverCustomer);

        boolean isfriends = dbcontroller.checkIfHasFriend(senderreturnedId, receiverreturnedId);
        if (isfriends){
            dbcontroller.insertIntoTransfers(senderreturnedId, receiverreturnedId, amountToSend);
        }
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