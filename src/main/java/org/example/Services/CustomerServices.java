package org.example.Services;

import org.example.Controller.TransferCalculator;
import org.example.Currencies.CurrencyAPI;
import org.example.Currencies.CurrencyEntity;
import org.example.Repositories.CustomerEntityRepository;
import org.example.Repositories.TransfersRepository;
import org.example.Model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class CustomerServices {

    TransfersRepository transfersrepository;
    CustomerEntityRepository customerentityrepository;
    TransferCalculator transactions;
    CurrencyEntity currencyEntity;
    final Logger logger = LoggerFactory.getLogger(CustomerServices.class);

    public CustomerServices(CustomerEntityRepository customerentityrepository, TransfersRepository transfersrepository, TransferCalculator transactions, CurrencyEntity currencyEntity) throws SQLException {
        this.customerentityrepository = customerentityrepository;
        this.transfersrepository = transfersrepository;
        this.transactions = transactions;
        this.currencyEntity = currencyEntity;
    }

    public void depositMoney(String customerName, int depositAmount) throws SQLException {

        int returnedId = customerentityrepository.findCustomerByUsername(customerName);
        Customer customer = customerentityrepository.returnRecord(returnedId);
        int oldAmount = customer.moneyInCents();
        int newAmount = transactions.receiveMoneyTransaction(oldAmount, depositAmount);
        boolean fundsupdated = customerentityrepository.updateCustomerFunds(returnedId, newAmount);
        if (fundsupdated){
            logger.info("{} money was updated by {}! ", customerName, depositAmount);
        }
    }

    public void makeTransfer(String nameSenderCustomer, String nameReceiverCustomer, int amountToSend) throws SQLException, IOException {
        int senderreturnedId = customerentityrepository.findCustomerByUsername(nameSenderCustomer);
        int receiverreturnedId = customerentityrepository.findCustomerByUsername(nameReceiverCustomer);

        boolean isMoneyReceived = false;

        boolean isMoneySent = sendMoneyToSomeone(senderreturnedId, amountToSend);
        if (isMoneySent == true) {
            isMoneyReceived = receiveMoney(receiverreturnedId, amountToSend);
            System.out.println(isMoneyReceived + " " + isMoneySent);
            logger.info("money was sent");
        }
        if (isMoneyReceived == true) {
            logger.info("money was received");
            updateDbAfterTransfer(senderreturnedId, nameSenderCustomer, receiverreturnedId, nameReceiverCustomer, amountToSend);
        } else logger.error("the money wasn't received, it returned {}", isMoneyReceived);

    }
// TODO: read about happy path
    // TODO: what if funds insufficient? add error handlers

    public boolean sendMoneyToSomeone(int senderreturnedId, int amountToSend) throws SQLException {

        Customer sendercustomer = customerentityrepository.returnRecord(senderreturnedId);

        int senderMoney = sendercustomer.moneyInCents();
        boolean hasenough = transactions.checkIfCustomerHasFundsToMakeTransaction(senderMoney, amountToSend);
        if (hasenough) {
            int newsenderamount = transactions.sendMoneyTransaction(senderMoney, amountToSend);
            customerentityrepository.updateCustomerFunds(senderreturnedId, newsenderamount);
            return true;
        }
        else
            logger.info("insufficient amount in your bank account to do this transfer");
        return false;
    }

    public boolean receiveMoney(int receiverreturnedId, int amountToSend) throws SQLException {

        Customer receivercustomer = customerentityrepository.returnRecord(receiverreturnedId);
        int receiverMoney = receivercustomer.moneyInCents();
        int newreceiveramount = transactions.receiveMoneyTransaction(receiverMoney, amountToSend);
        boolean receiverFundsUpdated = customerentityrepository.updateCustomerFunds(receiverreturnedId, newreceiveramount);
        if (receiverFundsUpdated) {
            return true;
        } else return false;
    }

    public void updateDbAfterTransfer(int senderreturnedId, String nameSenderCustomer, int receiverreturnedId, String nameReceiverCustomer, int amountToSend) throws SQLException {

        transfersrepository.insertSenderReceiverIntoTransferTotals(senderreturnedId, receiverreturnedId, amountToSend);
        transfersrepository.insertReceiverSenderIntoTransferTotals(senderreturnedId, receiverreturnedId, amountToSend);
        transfersrepository.createTransactionAudit(senderreturnedId, nameSenderCustomer, amountToSend, receiverreturnedId, nameReceiverCustomer);

        boolean isfriends = customerentityrepository.checkIfHasFriend(senderreturnedId, receiverreturnedId);
        if (isfriends){
            transfersrepository.insertIntoTransfers(senderreturnedId, receiverreturnedId, amountToSend);
        }
    }

    public void convertMyFundsToAllCurrencies(String nameCustomer) throws IOException, SQLException {

        HashMap<String, Double> currencies = currencyEntity.getCurrencies();
        int customerId = customerentityrepository.findCustomerByUsername(nameCustomer);
        Customer returnedCustomerObj = customerentityrepository.returnRecord(customerId);
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

// receiving could be a separate function, if something breaks, there should be some intermediary to hold the
// transaction, for example, money is already being sent, but due to connection loss receiver didn't receive,
// so money is put on hold somewhere? Or is it actually safer just to do in one go with one function, since
// it will already gonna hold both customers objects