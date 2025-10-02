package org.example.Repositories;

import org.example.Model.Counterparty;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CounterPartyRep extends CrudRepository<Counterparty, UUID> {
}
