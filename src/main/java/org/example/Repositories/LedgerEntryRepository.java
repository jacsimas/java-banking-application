package org.example.Repositories;

import org.example.Model.LedgerEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LedgerEntryRepository extends CrudRepository<LedgerEntry, Long> {
    void save();

    void save(UUID transactionId, UUID debtorAccountId, String currency, int amount, String dr, String description);
}
