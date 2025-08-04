package org.example.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private int money;
    private String password;
    private String email;
    private boolean debit_card;

    public Customers(String nickname,int money, String password, String email, boolean debit_card){

        this.nickname = nickname;
        this.money = money;
        this.password = password;
        this.email = email;
        this.debit_card = debit_card;
    }

    public Customers(){
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDebit_card(boolean debit_card) {
        this.debit_card = debit_card;
    }

    public boolean getDebit_card() {
        return debit_card;
    }
}
