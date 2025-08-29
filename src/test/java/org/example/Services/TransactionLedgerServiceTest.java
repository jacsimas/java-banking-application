package org.example.Services;

import org.example.Model.TransactionLedger;
import org.example.Repositories.TransferAuditRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class TransactionLedgerServiceTest {

    @Mock
    TransferAuditRepository transferAuditRepository;
    @InjectMocks
    TransactionLedgerService transactionLedgerService;

//    @Test
//    public void updateTransactionLedgerTest(){
//     //   {\"senderId\":1,\"senderNickname\":\"derry\",\"amountSent\":100,\"receiverId\":2,\"receiverNickname\":\"john\"}"
//
//        TransactionLedger payment = new TransactionLedger(1, "derry");
//        assertThat().isEqual.
//    }

}
