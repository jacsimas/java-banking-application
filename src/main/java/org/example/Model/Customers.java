package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private int moneyInCents;
    private String password;
    private String email;
    private boolean debitcard;

    public Customers(Long id, String user,int moneyInCents, String password, String email, boolean debitcard){
        this.id = id;
        this.user = user;
        this.moneyInCents = moneyInCents;
        this.password = password;
        this.email = email;
        this.debitcard = debitcard;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoneyInCents() {
        return moneyInCents;
    }

    public void setMoneyInCents(int moneyInCents) {
        this.moneyInCents = moneyInCents;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDebitcard(boolean debitcard) {
        this.debitcard = debitcard;
    }

    public boolean getDebitcard() {
        return debitcard;
    }
}
