package org.example.Controller;


import org.example.Model.Customers;
import org.example.Services.CustomerEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    CustomerEntityService customerEntityService;

    public CustomerController(CustomerEntityService customerEntityService) {
        this.customerEntityService = customerEntityService;
    }

    final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public List<Customers> getAll() {
        return customerEntityService.allCustomers();
    }

    @GetMapping("{id}")
    public Optional<Customers> getById(@PathVariable long id) {
        return customerEntityService.auditById(id);
    }

    @GetMapping("/name/{nickname}")
    public List<Customers> getByNickname(@PathVariable String nickname) {
        return customerEntityService.byNickname(nickname);
    }

    @GetMapping("/other-id")
    public Optional<Customers> getId(@RequestParam(value = "id", defaultValue = "1") long id) throws SQLException {
        return Optional.ofNullable(customerEntityService.getCustomersbyid(id));
    } // http://localhost:8080/customers/otherid?id=5

    @PatchMapping("add-money/{id}/{money}")
    public void send(@PathVariable long id, @PathVariable int money) {
        customerEntityService.changeAmount(id, money);
    }

//    @PatchMapping("moneySent/{sender}/{receiver}/{money}")
//    public void send(@PathVariable long sender, @PathVariable long receiver, @PathVariable int money) {
//        customerEntityService.updateSenderReceiver(sender, receiver, money);
//    }

    @PostMapping("/register")
    public void registerCustomer(@RequestBody Customers customer){
        customerEntityService.saveNewCustomer(customer);
    }

//    @PostMapping("/payments")
//    public void makePayment(@RequestBody ){
//// send a transaction JSON and save it in transactions_audit table, also use the data to update customers table
//    }

}
