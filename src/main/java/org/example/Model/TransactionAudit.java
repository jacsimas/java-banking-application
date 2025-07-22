package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "transactions_audit")
public class TransactionAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int senderId;
    private String senderName;
    private int moneyInCents;
    private int getterId;
    private String getterName;
    private String time;

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public int getMoneyInCents() {
        return moneyInCents;
    }

    public int getGetterId() {
        return getterId;
    }

    public String getGetterName() {
        return getterName;
    }

    public String getTime() {
        return time;
    }
}
