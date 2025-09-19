package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    private UUID transaction_id;
    private String type;
    private String status;
    private UUID debtor_account_id;
    private UUID  creditor_account_id;
    private int amount;
    private String currency;
    private String reference;



}
