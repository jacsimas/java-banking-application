package org.example.Services;

import org.example.Controller.TransactionController;
import org.example.DBController;
import org.example.Model.Customer;

import java.io.IOException;
import java.sql.SQLException;


public class CustomerServices {

    DBController dbcontroller = new DBController();
    TransactionController transactions = new TransactionController();

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


    public boolean sendMoneyToSomeone(String nameSenderCustomer, String nameReceiverCustomer, int amountToSend) throws IOException, SQLException {

        int senderreturnedId = dbcontroller.findCustomerByUsername(nameSenderCustomer);
        Customer sendercustomer = dbcontroller.returnRecord(senderreturnedId);
        int receiverreturnedId = dbcontroller.findCustomerByUsername(nameReceiverCustomer);
        Customer receivercustomer = dbcontroller.returnRecord(receiverreturnedId);

        int senderMoney = sendercustomer.getMoneyInCents();
        int receiverMoney = receivercustomer.getMoneyInCents();
        boolean hasenough = transactions.checkIfCustomerHasFundsToMakeTransaction(senderMoney, amountToSend);
        if (hasenough) {
            int newsenderamount = transactions.sendMoneyTransaction(senderMoney, amountToSend);
            int newreceiveramount = transactions.receiveMoneyTransaction(receiverMoney, amountToSend);
            dbcontroller.updateCustomerFunds(senderreturnedId, newsenderamount);
            dbcontroller.updateCustomerFunds(receiverreturnedId, newreceiveramount);
            dbcontroller.createTransactionAudit(senderreturnedId,nameSenderCustomer, amountToSend, receiverreturnedId, nameReceiverCustomer);
            return true;
        }
        return false;
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