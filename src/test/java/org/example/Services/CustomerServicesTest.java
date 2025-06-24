package org.example.Services;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerServicesTest {

    CustomerServices customerActionsController = new CustomerServices();

    public CustomerServicesTest() throws SQLException {
    }

    @Test
    public void depositMoneyTest () throws IOException, SQLException {
        String name = "jennifer";
        int deposit = 22000;

       // int moneydepositedupdated = customerActionsController.depositMoney(name, deposit);
    
      //  assertThat(moneydepositedupdated).isEqualTo(1);

    }
//
    @Test
    public void sendMoneyToSomeoneTest() throws SQLException, IOException {

        // what if transaction breaks for some reason? or if he doesn't receive money? or if she sends money,
        // but he doesn't receive?
    }

    @Test
    public void convertMyFundsToAllCurrenciesTest() throws SQLException, IOException {
// TODO: finish this test or delete
        CustomerServices cservice = new CustomerServices();
        cservice.convertMyFundsToAllCurrencies("jennifer");

    }

}
