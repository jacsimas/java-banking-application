package org.example.Controller;

import org.example.Model.Customers;
import org.example.Services.CustomerEntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerControllerTest {

    CustomerEntityService customerEntityService;

    public CustomerControllerTest(CustomerEntityService customerEntityService) {
        this.customerEntityService = customerEntityService;
    }


    @GetMapping
    public List<Customers> getAllTest() {
        return customerEntityService.allCustomers();
    }



}
