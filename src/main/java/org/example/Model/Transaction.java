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


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UUID getCreditor_account_id() {
        return creditor_account_id;
    }

    public void setCreditor_account_id(UUID creditor_account_id) {
        this.creditor_account_id = creditor_account_id;
    }

    public UUID getDebtor_account_id() {
        return debtor_account_id;
    }

    public void setDebtor_account_id(UUID debtor_account_id) {
        this.debtor_account_id = debtor_account_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(UUID transaction_id) {
        this.transaction_id = transaction_id;
    }
}
