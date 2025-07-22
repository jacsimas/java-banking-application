package org.example.Repositories;

import org.example.Model.TransactionAuditRecord;
import org.springframework.data.repository.CrudRepository;

public interface TransSpringRep extends CrudRepository<TransactionAuditRecord, Long> {
  TransactionAuditRecord findbyId(int id);
}
