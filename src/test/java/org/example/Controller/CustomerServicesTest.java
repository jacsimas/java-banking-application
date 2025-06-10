package org.example.Controller;

import org.example.Services.CustomerServices;
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

        customerActionsController.sendMoneyToSomeone("jennifer", "john", 4000);
        // what if transaction breaks for some reason? or if he doesn't receive money? or if she sends money,
        // but he doesn't receive?
    }


}
