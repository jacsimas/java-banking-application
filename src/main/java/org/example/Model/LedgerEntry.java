package org.example.Model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ledger_entry")
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private UUID transactionId;
    private UUID accountId;
    private String currency;
    private int amount;
    private String side;
    private String eventTime;
    private String description;


    public LedgerEntry() {
    }

    public long getid() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String geteventTime() {
        return eventTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UUID getaccountId() {
        return accountId;
    }

    public void setaccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID gettransactionId() {
        return transactionId;
    }

    public void settransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
}
