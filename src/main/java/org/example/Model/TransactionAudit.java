package org.example.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions_audit")
public class TransactionAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sender_id;
    private String sender_nickname;
    private int amount_sent;
    private int receiver_id;
    private String receiver_nickname;
    private String time;

    public TransactionAudit() {}

    public TransactionAudit(int sender_id, String sender_nickname, int amount_sent, int receiver_id, String receiver_nickname, String time){
        this.sender_id =sender_id;
        this.sender_nickname = sender_nickname;
        this.amount_sent=amount_sent;
        this.receiver_id = receiver_id;
        this.receiver_nickname=receiver_nickname;
        this.time=time;
    }

    public long getId() {
        return id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public String getSender_nickname() {
        return sender_nickname;
    }

    public int getAmount_sent() {
        return amount_sent;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public String getReceiver_nickname() {
        return receiver_nickname;
    }

    public String getTime() {
        return time;
    }
}
