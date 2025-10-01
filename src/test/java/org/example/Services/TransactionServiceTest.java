package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.Model.Account;
import org.example.Model.Customer;
import org.example.Model.Transaction;
import org.example.Repositories.AccountRepository;
import org.example.Repositories.CustomerRepository;
import org.example.Repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TransactionServiceTest {


    UUID debitorId = UUID.fromString("77a4f3f9-eea3-4861-9c8e-907c21d5cc7a");
    UUID creditorId = UUID.fromString("66599b3e-ead5-4822-9c95-69e3bc11541f");

    Transaction transaction = new Transaction();

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService transactionService;

    @Test
    void contextLoads() { /* if this passes, Flyway + JPA booted against the container */ }

//    @BeforeEach
//    void addToTransactionTable(){
//        when(transaction = transactionService.updateTransactionTable("P2P", "PENDING", debitorId, creditorId, 100, "EURO", "none" ));
//        when(transaction = transactionService.updateTransactionTable("P2P", "PENDING", debitorId, creditorId, 100, "EURO", "none" ));
////        transaction = transactionService.updateTransactionTable(debitorId, creditorId, 100, "EURO", "none" );
////        transaction = transactionService.updateTransactionTable(debitorId, creditorId, 100, "EURO", "none" );
//
//    }
//
//    //TODO: doesn't work with h2 imitational database so far. change or delete
//    @Test
//    public void getTransactionCount(){
//        long transCount = transactionService.getCount();
//
//
//        Assertions.assertTrue(transCount == 0);
//    }

}
