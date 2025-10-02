package org.example.Repositories;

import org.example.Model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
}
