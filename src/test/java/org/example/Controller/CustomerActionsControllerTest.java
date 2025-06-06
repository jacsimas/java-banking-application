package org.example.Controller;

import org.example.Model.Customer;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomerActionsControllerTest {

    @Test
    public void depositMoneyTest () throws IOException, SQLException {
        String name = "jennifer";
        int deposit = 22000;

        CustomerActionsController customerActionsController = new CustomerActionsController();
        int moneydepositedupdated = customerActionsController.depositMoney(name, deposit);

        assertThat(moneydepositedupdated).isEqualTo(1);

    }
//
//    @Test
//    public void sendMoneyToSomeone() {
//
//        CustomerActionsController cacontroller = new CustomerActionsController();
//        int idSenderCustomer;
//        int idReceiverCustomer;
//        int amountToSend;
//        cacontroller.sendMoneyToSomeone(idSenderCustomer, idReceiverCustomer, amountToSend);
//    }


}
