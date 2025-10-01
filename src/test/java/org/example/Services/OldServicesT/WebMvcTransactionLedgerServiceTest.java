package org.example.Services.OldServicesT;

import org.example.Repositories.OldRepositories.TransferLedgerRepository;
import org.example.Services.OldService.TransactionLedgerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class WebMvcTransactionLedgerServiceTest {

    @Mock
    TransferLedgerRepository transferLedgerRepository;
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
