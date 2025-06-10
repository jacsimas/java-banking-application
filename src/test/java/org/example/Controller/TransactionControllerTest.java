package org.example.Controller;

import org.example.DBController;
import org.example.Model.Customer;
import org.example.Model.TransactionAudit;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionControllerTest {
    // I guess this class should implement the main logic
    // UserModel expectedUser = new UserModel(55, "bb", 100);

    @Test
            public void returnAuditRecordTest() throws SQLException {
    DBController dbcontroller = new DBController();

    TransactionAudit returnedAudit =  dbcontroller.returnTransactionAudit(1);

    int senderid = returnedAudit.senderId();
    String sendername = returnedAudit.senderName();
    int amount = returnedAudit.moneyInCents();
    int getterid = returnedAudit.getterId();
    String gettername = returnedAudit.getterName();
    String time = returnedAudit.time();

        System.out.println(senderid + " , " + sendername + " , " + amount + " , " + getterid + " , " + gettername + " , " + time);
    //drivermanager.addCustomerRecord(name, money, password);

}
}
