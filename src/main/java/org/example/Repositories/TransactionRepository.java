package org.example.Repositories;

import org.example.Model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

}
