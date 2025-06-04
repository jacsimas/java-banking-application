package org.example.Controller;

import org.example.Model.Customer;

public class MeController {

  /*  public UserModel getUser() {

        return new UserModel(55, "bb", 100);
    }
*/
    public Customer getCustomer(){                  // if I want controller to take different customer each time, I handle logic here?
        return new Customer(50, "ss", 5000);
    }
}
// will get user from .vsc file, and we will set user, through cmd interface for example.
// int x, String b, int money

// So now as I understand, here could be the controller that catches all user actions and sends data to other
// controllers. meaning, invokes methods