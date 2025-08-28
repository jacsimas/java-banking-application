package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import org.example.Model.TransactionLedger;
import org.example.Repositories.TransferAuditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionLedgerService {

    TransferAuditRepository repository;
    TransactionLedger transactionLedger;

    public TransactionLedgerService(TransferAuditRepository repository, TransactionLedger transactionLedger){
        this.repository = repository;
        this.transactionLedger = transactionLedger;
    }

    public List<TransactionLedger> allAudits() {
        return (List<TransactionLedger>) repository.findAll();
    }

    public Optional<TransactionLedger> auditById(long id) {
     return repository.findById(id);
    }

    public TransactionLedger getTransactionAudit(long id){
        return repository.findById(id)
                .orElseThrow(() ->
                new EntityNotFoundException("Row " + id + " not found"));
    }

    public void updateTransactionLedger(TransactionLedger transactionLedger){
        repository.save(transactionLedger);
    }

}
