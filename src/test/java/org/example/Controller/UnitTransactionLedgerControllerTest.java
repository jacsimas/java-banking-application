package org.example.Controller;

import org.example.Model.TransactionLedger;
import org.example.Repositories.TransferAuditRepository;
import org.example.Services.TransactionLedgerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionLedgerController.class)
public class UnitTransactionLedgerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TransactionLedgerService transactionLedgerService;

    @MockitoBean
    TransferAuditRepository repository;

//    @MockitoBean
//    TransactionLedger transactionLedger;

    //TODO: find out how this one works with POST, now its only for GET
    @org.junit.jupiter.api.Test
    void shouldMakeTheTransferMockedService() throws Exception {
        when(transactionLedgerService.getTransactionAudit(1))
                .thenReturn(new TransactionLedger());
        this.mockMvc.
                perform(post("/payments/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senderId\":1,\"senderNickname\":\"derry\",\"amountSent\":100,\"receiverId\":2,\"receiverNickname\":\"john\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
// transactionLedger:  1, "derry", 100, 2, "john"