package org.example.Repositories;

import org.example.Model.TransactionLedger;
import org.springframework.data.repository.CrudRepository;

public interface TransferLedgerRepository extends CrudRepository<TransactionLedger, Long> {
}
