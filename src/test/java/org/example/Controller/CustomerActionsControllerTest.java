package org.example.Controller;

import org.example.Model.Customer;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerActionsControllerTest {

    Customer actualCustomer = new Customer(54, "bb", 4200);
  //  int actualCustomerMoney = actualCustomer.getMoneyInCents();
   // int idSenderCustomer = 4;
  //  Customer expectedCustomer = new Customer(5, "johhny", 5000);
   // int idReceiverCustomer = 24;
    Customer senderCustomer = new Customer(7 , "sasha" , 3700);
    Customer receiverCustomer = new Customer(2 , "brad" , 8000);
    String filePath = "src/main/java/org/example/Output_files/allcustomers.csv";
    CustomerActionsController cacontroller = new CustomerActionsController();

    @Test
    public void testUserSelectHowMuchToTransfer(){  // User places a number value for a transfer

    }

    @Test
    public void testSelectToWhomMakeTransaction() { // User selects to which other user to transfer the amount

    }

    @Test
    public void putMoneyIntoAccountTest() throws IOException {
        int depositAmount = 400;
        int actualCustomerId = actualCustomer.getId();
        CustomerActionsController customeractionscontroller = new CustomerActionsController();
        int afterDeposit = customeractionscontroller.putMoneyIntoAccount(3, depositAmount);

        assertThat(afterDeposit).isEqualTo(20800);
    }

    @Test
    public void sendMoneyToSomeoneTest() throws IOException {
        int amountToSend = 3000;
        CustomerActionsController customeractionscontroller = new CustomerActionsController();

        boolean moneySent =  customeractionscontroller.sendMoneyToSomeone(7, 1, amountToSend);

        assertThat(moneySent).isTrue();
        // deletes spaces in between names, but it supposed to be username not name and surname anyway.
    }

    @Test
    public void createNewCustomerTest() throws IOException {
        String customerName = "dave";
        int firstDeposit = 150;

        CustomerActionsController cacontroler = new CustomerActionsController();
        cacontroler.createNewCustomer(customerName, firstDeposit);
    }

    @Test
    public void sendFriendRequestTest() throws IOException {

        boolean requestSent = cacontroller.sendFriendRequest(senderCustomer, receiverCustomer);

        assertThat(requestSent).isTrue();
    }

    @Test
    public void acceptFriendRequestTest() throws IOException {

        boolean requestAccepted = cacontroller.acceptFriendRequest(receiverCustomer, senderCustomer);

        assertThat(requestAccepted).isTrue();
    }
}
