package org.example.Repositories.OldRepositories;

import org.example.Model.OldModels.TransactionLedger;
import org.springframework.data.repository.CrudRepository;

public interface TransferLedgerRepository extends CrudRepository<TransactionLedger, Long> {
}
