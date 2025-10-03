package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.ModelDTO.TransactionDTO;
import org.example.Repositories.AccountRepository;
import org.example.Repositories.TransactionRepository;
import org.example.TransfersApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class TransactionService {

    final Logger log = LoggerFactory.getLogger(TransfersApplication.class);

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
        transaction.setType(transactionDTO.getType());
        transaction.setStatus(transactionDTO.getStatus());


        UUID debtorsId = transactionDTO.getDebtorAccountId();
        log.info("printing -- {}", debtorsId);

        int amount = transactionDTO.getAmount();
       if (sufficientFunds(debtorsId, amount))
       {         transactionRepository.save(transaction);
           return true; }
        else return false;
    }

    public long getCount() {
        return transactionRepository.count();
    }

    public boolean sufficientFunds(UUID debtorAccountId, int amount) {

        Optional<Account> acc = accountRepository.findById(debtorAccountId);
        Account account = acc.orElseThrow(() -> new RuntimeException("Account not found"));
         if (account.getBalance() >= amount){
             return true;
         } else return false;
    }

}
