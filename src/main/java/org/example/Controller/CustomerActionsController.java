package org.example.Controller;

import org.example.Model.Customer;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class CustomerActionsController{

/*
    public int putMoneyIntoAccount(int actualCustomerId, int depositAmount) throws IOException {

    }


    public boolean sendMoneyToSomeone(int idSenderCustomer, int idReceiverCustomer, int amountToSend) throws IOException {

    }

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