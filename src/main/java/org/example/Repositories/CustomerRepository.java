package org.example.Repositories;

import org.example.Model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
