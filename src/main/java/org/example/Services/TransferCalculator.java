package org.example.Services;

import org.springframework.stereotype.Service;

@Service
public class TransferCalculator {

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
