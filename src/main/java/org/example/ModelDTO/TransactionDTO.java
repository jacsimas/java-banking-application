package org.example.ModelDTO;

import java.util.UUID;

public class TransactionDTO {

    private String type;
    private String status;
    private UUID debtorAccountId;
    private UUID  creditorAccountId;
    private int amount;
    private String currency;
    private String reference;

    public UUID getDebtorAccountId() {
        return debtorAccountId;
    }

    public void setDebtorAccountId(UUID debtorAccountId) {
        this.debtorAccountId = debtorAccountId;
    }

    public UUID getCreditorAccountId() {
        return creditorAccountId;
    }

    public void setCreditorAccountId(UUID creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
