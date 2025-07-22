package org.example.Controller;

import org.example.Repositories.TransSpringRep;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TransactionAuditController {

    public TransactionAuditController(){

    }

    TransSpringRep repository;

    @GetMapping("/transactionaudit")
    public String showTransactionAudit(@RequestParam(value="id", defaultValue = "1") int id) throws SQLException {

        return String.format("%s", repository.findbyId(id));
    }
}
// http://localhost:8080/transactionaudit?id=2

// transfersrepository.returnTransactionAudit(id)