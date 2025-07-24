package org.example.Repositories;

import org.example.Model.TransactionAudit;
import org.springframework.data.repository.CrudRepository;

public interface TransferAuditRepository extends CrudRepository<TransactionAudit, Long> {
 //  TransactionAudit findbyId(long id);
}
