package org.example.Repositories;

import jakarta.persistence.EntityNotFoundException;
import org.example.Model.TransactionAudit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferAuditService {

    TransferAuditRepository repository;

    public TransferAuditService(  TransferAuditRepository repository){
        this.repository = repository;
    }

    public List<TransactionAudit> allAudits() {
        return (List<TransactionAudit>) repository.findAll();
    }

    public Optional<TransactionAudit> auditById(long id) {
     return repository.findById(id);
    }

    public TransactionAudit getTransactionAudit(long id){
        return repository.findById(id)
                .orElseThrow(() ->
                new EntityNotFoundException("Row " + id + " not found"));
    }

}
// Cannot invoke "org.example.Repositories.TransSpringRep.findById(Object)" because "this.repository" is null