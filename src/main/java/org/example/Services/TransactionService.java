package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.ModelDTO.TransactionDTO;
import org.example.Repositories.AccountRepository;
import org.example.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
public class TransactionService {

    TransactionRepository transactionRepository;
    Transaction transaction = new Transaction();
    AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
     this.transactionRepository = transactionRepository;
     this.accountRepository = accountRepository;
    }

    public boolean updateTransactionTable(TransactionDTO transactionDTO){
//        Optional<Account> debtor = accountRepository.findById(debtorAccountId);
//        Optional<Account> creditor = accountRepository.findById(creditorAccountId);

        transaction.setdebtorAccountId(transactionDTO.getDebtorAccountId());
        transaction.setcreditorAccountId(transactionDTO.getCreditorAccountId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCurrency(transactionDTO.getCurrency());
        transaction.setReference(transactionDTO.getReference());

        transactionRepository.save(transaction);
        return true;
    }

    public long getCount() {
        return transactionRepository.count();
    }

    public boolean sufficientFunds(UUID debitor, int amount) {

        Account acc = accountRepository.findById(debitor)
                .orElseThrow(() ->
                        new EntityNotFoundException("Row " + debitor + " not found"));
         if (acc.getBalance() >= amount){
             return true;
         } else return false;
    }

}
