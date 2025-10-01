package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.ModelDTO.TransactionDTO;
import org.example.Repositories.AccountRepository;
import org.example.Repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockitoTransactionServiceTest {

    UUID debitorAccountId = UUID.fromString("77a4f3f9-eea3-4861-9c8e-907c21d5cc7a");
    UUID creditorAccountId = UUID.fromString("66599b3e-ead5-4822-9c95-69e3bc11541f");

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    Transaction transaction = new Transaction();
    Account account = new Account();
    int amountToTransfer = 100;

    @Mock
    AccountRepository accountRepository;

    //TODO: remake this test with mockito
    @Test
    public void getTransactionCount(){
        long transCount = transactionService.getCount();

        Assertions.assertEquals(0, transCount);
    }

    @Test
    void onlyUpdateTransactionTable(){

        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());
        transactionService.updateTransactionTable(new TransactionDTO());
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void checkIfReturnsAccountById(){
        int amount = transaction.getAmount();
        UUID id = transaction.getid();

        when(accountRepository.findById(any())).thenReturn(Optional.of(new Account()));
        transactionService.sufficientFunds(id, amount);
        verify(accountRepository).findById(any());

    }

    @Test
    void checkIfSufficientFunds(){

    }

    @Test
    void contextLoads() {  }

}
