package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import org.example.Model.TransactionLedger;
import org.example.Repositories.TransferLedgerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionLedgerService {

    TransferLedgerRepository repository;

    public TransactionLedgerService(TransferLedgerRepository repository){
        this.repository = repository;
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
