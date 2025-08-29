package org.example.Repositories;

import org.example.Model.TransactionLedger;
import org.springframework.data.repository.CrudRepository;

public interface TransferAuditRepository extends CrudRepository<TransactionLedger, Long> {
}
