package org.example.Controller;

public class TransactionController{

    public int receiveMoneyTransaction( int receiverCustomerAmount, int transferAmount){
        return receiverCustomerAmount + transferAmount;
    }

    public int sendMoneyTransaction(int senderCustomerAmount, int transferAmount){
        return senderCustomerAmount - transferAmount;
    }

    public boolean checkIfCustomerHasFundsToMakeTransaction(int customerHeldAmount, int transferAmount) {
        return customerHeldAmount > transferAmount;
    }
}
