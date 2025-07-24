package org.example.Controller;

import org.example.Model.TransactionAudit;
import org.example.Repositories.TransferAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactionaudit")
public class TransactionAuditController {

    TransferAuditService transferAuditService;

    public TransactionAuditController( TransferAuditService transferAuditService){
this.transferAuditService = transferAuditService;
    }

    final Logger log = LoggerFactory.getLogger(TransactionAuditController.class);

@GetMapping
public List<TransactionAudit> getAll(){
    return transferAuditService.allAudits();
}

    @GetMapping("row/{id}")
    public Optional<TransactionAudit> byId(@PathVariable long id){
    return transferAuditService.auditById(id);
    }

    @GetMapping("/else")         //produces = MediaType.TEXT_PLAIN_VALUE
    public Optional<TransactionAudit> showTransactionAudit(@RequestParam(value="id", defaultValue = "1") long id) throws SQLException {

    return transferAuditService.auditById(id);
    }

}


// http://localhost:8080/transactionaudit?id=2      or 127.

