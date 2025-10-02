package org.example.Services;

import org.example.Model.LedgerEntry;
import org.example.Model.Transaction;
import org.example.Repositories.LedgerEntryRepository;

import java.util.UUID;

public class LedgerEntryService {

    LedgerEntryRepository ledgerEntryRepository;

    public LedgerEntryService(LedgerEntryRepository ledgerEntryRepository){
        this.ledgerEntryRepository = ledgerEntryRepository;

    }
    public void updateLedgerEntryTable(Transaction transaction){

        UUID transaction_id = transaction.getid();
        UUID debtor_account_id = transaction.getdebtorAccountId();
        UUID  creditor_account_id = transaction.getcreditorAccountId();
        int amount = transaction.getAmount();
        String currency = transaction.getCurrency();
        String description = transaction.getReference();

        LedgerEntry ledgerEntry = new LedgerEntry();

        ledgerEntry.settransactionId(transaction_id);
        ledgerEntry.setaccountId(debtor_account_id);
        ledgerEntry.setAmount(amount);
        ledgerEntry.setCurrency(currency);
        ledgerEntry.setDescription(description);

        //TODO: not sure yet how does this double entry thing work, will have to investigate
        ledgerEntryRepository.save(ledgerEntry);

        ledgerEntry.settransactionId(transaction_id);
        ledgerEntry.setaccountId(creditor_account_id);
        ledgerEntry.setAmount(amount);
        ledgerEntry.setCurrency(currency);
        ledgerEntry.setDescription(description);

        ledgerEntryRepository.save(ledgerEntry);

    }

}
