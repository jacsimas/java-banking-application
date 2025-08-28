package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Services.TransactionLedgerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransactionLedgerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionLedgerService transactionLedgerService;

    @Test
    void shouldMakeTheTransfer() throws Exception {
//        when(transactionLedgerService.updateTransactionLedger()).thenReturn("done");
        this.mockMvc.
                perform(post("/payments/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senderId\":1,\"senderNickname\":\"derry\",\"amountSent\":100,\"receiverId\":2,\"receiverNickname\":\"john\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
