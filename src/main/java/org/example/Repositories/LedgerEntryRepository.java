package org.example.Repositories;

import org.example.Model.LedgerEntry;
import org.springframework.data.repository.CrudRepository;

public interface LedgerEntryRepository extends CrudRepository<LedgerEntry, Long> {
}
