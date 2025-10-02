package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;    // does it share the same with customer table uuid?
    private UUID customerId;
    private String currency;
    private String iban;
    private String bic;
    private int balance;
    private String status;
    private String createdAt;


    public UUID getid() {
        return id;
    }

    public void setid(UUID id) {
        this.id = id;
    }

    public UUID getcustomerId() {
        return customerId;
    }

    public void setcustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
