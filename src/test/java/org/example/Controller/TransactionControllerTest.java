package org.example.Controller;

import org.example.Model.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionControllerTest {
    // I guess this class should implement the main logic
    // UserModel expectedUser = new UserModel(55, "bb", 100);

    @Before
            public void setup(){}

    Customer expectedCustomer = new Customer(54, "bb", 3000);

    MeController mecontroller = new MeController();
    Customer actualCustomer = mecontroller.getCustomer();
    int transferAmount = 50;
    TransactionController transactioncontroller = new TransactionController();


    @Test
    public void testCheckIfCustomerHasFundsToMakeTransaction(){    // checks if user has funds in his 'account' to make a wanted transaction

        int expectedCustomerHeldAmount;

        int transferAmount = 2000;

        expectedCustomerHeldAmount = expectedCustomer.getMoneyInCents();

        boolean heldAmountIsgreater = transactioncontroller.checkIfCustomerHasFundsToMakeTransaction(expectedCustomerHeldAmount, transferAmount);

        assertThat(true).isEqualTo(heldAmountIsgreater);
    }

    // needs a different method to check. actual value has to be equal or higher than expected.
       /* assertThat(expectedCustomerHeldAmount)
                .usingRecursiveComparison()
                .isEqualTo(actualCustomerHeldAmount);

*/
    @Test
    public void testSenderCustomerAmountAfterTransaction(){  // Test to see if the transfer was performed by the expected user.


        int senderCustomerAmount = expectedCustomer.getMoneyInCents();

        int returnedAmount = transactioncontroller.sendMoneyTransaction(senderCustomerAmount, transferAmount);

        expectedCustomer.setMoneyInCents(returnedAmount);
        int senderCustomerAmountAfter = expectedCustomer.getMoneyInCents();

        assertThat(senderCustomerAmountAfter).isEqualTo(2950);
    }

    @Test
    public void testReceiverCustomerAmountAfterTransaction(){
        int receiverCustomerAmount = actualCustomer.getMoneyInCents();
        int returnedAmount = transactioncontroller.receiveMoneyTransaction(receiverCustomerAmount, transferAmount);

        actualCustomer.setMoneyInCents(returnedAmount);
        int receiverCustomerAmountAfter = actualCustomer.getMoneyInCents();

        assertThat(receiverCustomerAmountAfter).isEqualTo(5050);
    }


}
