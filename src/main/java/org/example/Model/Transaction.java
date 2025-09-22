package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    private UUID id;
    private String type;
    private String status;
    private UUID debtorAccountId;
    private UUID  creditorAccountId;
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

    public UUID getcreditorAccountId() {
        return creditorAccountId;
    }

    public void setcreditorAccountId(UUID creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
    }

    public UUID getdebtorAccountId() {
        return debtorAccountId;
    }

    public void setdebtorAccountId(UUID debtorAccountId) {
        this.debtorAccountId = debtorAccountId;
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

    public UUID getid() {
        return id;
    }

    public void setid(UUID id) {
        this.id = id;
    }
}
