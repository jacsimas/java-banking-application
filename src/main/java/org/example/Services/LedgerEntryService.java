package org.example.Services;

import org.example.Model.Transaction;
import org.example.Repositories.LedgerEntryRepository;

import java.util.UUID;

public class LedgerEntryService {

    LedgerEntryRepository ledgerEntryRepository;

    public LedgerEntryService(LedgerEntryRepository ledgerEntryRepository){
        this.ledgerEntryRepository = ledgerEntryRepository;

    }
    public void updateLedgerEntryTable(Transaction transaction){

        UUID transaction_id = transaction.getTransaction_id();
        UUID debtor_account_id = transaction.getDebtor_account_id();
        UUID  creditor_account_id = transaction.getCreditor_account_id();
        int amount = transaction.getAmount();
        String currency = transaction.getCurrency();
        String description = transaction.getReference();

        ledgerEntryRepository.save(transaction_id, debtor_account_id, currency, amount, "DR", description);

    }

}
