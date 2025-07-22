package org.example.Repositories;

import org.example.Model.TransactionAudit;
import org.springframework.data.repository.CrudRepository;

public interface TransSpringRep extends CrudRepository<TransactionAudit, Long> {
  TransactionAudit findbyId(int id);
}
