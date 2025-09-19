package org.example.Services;

import jakarta.transaction.Transactional;
import org.example.Model.LedgerEntry;
import org.example.Model.Transaction;
import org.example.Repositories.LedgerEntryRepository;
import org.example.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
public class TransactionService {

    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
     this.transactionRepository = transactionRepository;
    }

    public void updateTransactionTable(Transaction transaction){

        transactionRepository.save(transaction);

    }

}
