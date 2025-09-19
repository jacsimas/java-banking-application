package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    private UUID account_id;
    private long customer_id;
    private String currency;
    private String iban;
    private String bic;
    private int balance;
    private String status;
    private String created_at;


}
