package org.example.Controller;

import org.example.Model.TransactionLedger;
import org.example.Services.TransactionLedgerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionLedgerController.class)
@ExtendWith(MockitoExtension.class)
public class UnitTransactionLedgerControllerTest {


    MockMvc mockMvc;

    @Mock
    TransactionLedgerService transactionLedgerService;

    @InjectMocks
    TransactionLedgerController transactionLedgerController;

//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(transactionLedgerController).build(); // no Spring Boot, no DB
//    }

    //TODO: this one doesn't work either.
//    @Test
//    void shouldGetTransactionIdMockedService() throws Exception {
//        when(transactionLedgerService.auditById(any()))
//                .thenReturn(Optional.of(new TransactionLedger()));
//        this.mockMvc.
//                perform(get("/row/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"senderId\":1,\"senderNickname\":\"derry\",\"amountSent\":100,\"receiverId\":2,\"receiverNickname\":\"john\"}"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }


    //TODO: this one doesn't work with POST, and returns void.
//    @Test
//    void shouldMakeTheTransferMockedService() throws Exception {
//        when(transactionLedgerService.updateTransactionLedger(any()))
//                .thenReturn(new TransactionLedger());
//            mockMvc.
//                perform(post("/payments/transaction")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"senderId\":1,\"senderNickname\":\"derry\",\"amountSent\":100,\"receiverId\":2,\"receiverNickname\":\"john\"}"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

// transactionLedger:  1, "derry", 100, 2, "john"

}