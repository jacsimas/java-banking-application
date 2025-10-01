package org.example.Controller.OldControllers;

import org.example.Model.OldModels.TransactionLedger;
import org.example.ModelDTO.OldDTO.RequestTransactionLedgerDTO;
import org.example.Services.OldService.TransactionLedgerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class TransactionLedgerController {

    TransactionLedgerService transactionLedgerService;

    public TransactionLedgerController(TransactionLedgerService transactionLedgerService){
this.transactionLedgerService = transactionLedgerService;
    }

    final Logger log = LoggerFactory.getLogger(TransactionLedgerController.class);

@GetMapping
public List<TransactionLedger> getAll(){
    return transactionLedgerService.allAudits();
}

    @GetMapping("row/{id}")
    public TransactionLedger byId(@PathVariable long id){
    return transactionLedgerService.auditById(id);
    }

    @GetMapping("/else")
    public TransactionLedger showTransactionAudit(@RequestParam(value="id", defaultValue = "1") long id) throws SQLException {

    return transactionLedgerService.auditById(id);
    }

    @GetMapping("/test")
    public Optional<TransactionLedger> showtest(@RequestParam(value="id", defaultValue = "1") long id) throws SQLException {

        return Optional.ofNullable(transactionLedgerService.getTransactionAudit(id));
    }


    @PostMapping("/transaction")
    public void makeTheTransfer(@RequestBody RequestTransactionLedgerDTO data){
    transactionLedgerService.updateTransactionLedger(data);
    }

}


// http://localhost:8080/api/transactionaudit?id=2      or 127.

