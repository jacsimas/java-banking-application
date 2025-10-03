package org.example.Controller;

import org.example.ModelDTO.TransactionDTO;
import org.example.Services.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/send")
    public void makeTransaction(@RequestBody TransactionDTO data){
        transactionService.updateTransactionTable(data);
    }

}
