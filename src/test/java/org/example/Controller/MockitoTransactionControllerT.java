package org.example.Controller;

import org.example.Model.Transaction;
import org.example.ModelDTO.TransactionDTO;
import org.example.Repositories.TransactionRepository;
import org.example.Services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTransactionControllerT {

@Mock
    TransactionService transactionService;

@InjectMocks
    TransactionController transactionController;

    TransactionDTO transactionDTO = new TransactionDTO();
    Transaction transaction = new Transaction();

@Test
    void makeTransaction(){
    when(transactionService.updateTransactionTable(any(TransactionDTO.class))).thenReturn(true);
    transactionController.makeTransaction(new TransactionDTO());
    verify(transactionService).updateTransactionTable(any(TransactionDTO.class));

 //TODO figure this one out. check Gintaras's java project in github, read some stuff.
}

}
