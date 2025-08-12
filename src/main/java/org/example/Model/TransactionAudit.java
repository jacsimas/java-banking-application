package org.example.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions_audit")
public class TransactionAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int senderId;
    private String senderNickname;
    private int amountSent;
    private int receiverId;
    private String receiverNickname;
    private String time;

    public TransactionAudit() {}


    public long getId() {
        return id;
    }

    public int getSender_id() {
        return senderId;
    }

    public String getSender_nickname() {
        return senderNickname;
    }

    public int getAmount_sent() {
        return amountSent;
    }

    public int getReceiver_id() {
        return receiverId;
    }

    public String getReceiver_nickname() {
        return receiverNickname;
    }

    public String getTime() {
        return time;
    }
}
