package org.example.Controller;

import org.example.DriverManagerDB;
import org.example.Model.Customer;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;

public class DriverManagerDBTest {

    @Test
    public void addCustomerRecordTest() {

    }

    @Test
    public void returnRecord() throws SQLException {

        int customerId = 3;

        DriverManagerDB drivermanagerdb = new DriverManagerDB();
        Customer returnedCustomer = drivermanagerdb.returnRecord(customerId);
        String nickname = returnedCustomer.getUser();

        assertThat(nickname).isEqualTo("johnny");

    }
}
