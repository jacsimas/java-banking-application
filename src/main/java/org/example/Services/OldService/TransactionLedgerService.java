package org.example.Services.OldService;

import jakarta.persistence.EntityNotFoundException;
import org.example.Model.OldModels.TransactionLedger;
import org.example.ModelDTO.OldDTO.RequestTransactionLedgerDTO;
import org.example.Repositories.OldRepositories.TransferLedgerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionLedgerService {

    TransferLedgerRepository repository;
    TransactionLedger ledger = new TransactionLedger();

    public TransactionLedgerService(TransferLedgerRepository repository){
        this.repository = repository;
    }

    public List<TransactionLedger> allAudits() {
        return (List<TransactionLedger>) repository.findAll();
    }

    public TransactionLedger auditById(long id) {
     return repository.findById(id)
             .orElseThrow(() ->
             new EntityNotFoundException("Row " + id + " not found"));
    }

    public TransactionLedger getTransactionAudit(long id){
        return repository.findById(id)
                .orElseThrow(() ->
                new EntityNotFoundException("Row " + id + " not found"));
    }

    public void updateTransactionLedger(RequestTransactionLedgerDTO data){

     ledger.setSenderId(data.getSenderId());
     ledger.setSenderNickname(data.getSenderNickname());
     ledger.setAmountSent(data.getAmountSent());
     ledger.setReceiverId(data.getReceiverId());
     ledger.setReceiverNickname(data.getReceiverNickname());

     repository.save(ledger);
    }

}
