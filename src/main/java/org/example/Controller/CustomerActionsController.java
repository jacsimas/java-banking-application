package org.example.Controller;

import org.example.DBController;
import org.example.Model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class CustomerActionsController{

    DBController dbcontroller = new DBController();

    public CustomerActionsController() throws SQLException {
    }

    public int depositMoney(String customerName, int depositAmount) throws IOException, SQLException {

        int returnedId = dbcontroller.findCustomerByUsername(customerName);
        Customer customer = dbcontroller.returnRecord(returnedId);
        TransactionController transactions = new TransactionController();
        int oldAmount = customer.getMoneyInCents();
        int newAmount = transactions.receiveMoneyTransaction(oldAmount, depositAmount);
        int deposited = dbcontroller.updateCustomerFunds(returnedId, newAmount);
        if (deposited == 1){
            return 1;
        }
        else return 0;

    }


//    public boolean sendMoneyToSomeone(int idSenderCustomer, int idReceiverCustomer, int amountToSend) throws IOException {
//
//    }

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