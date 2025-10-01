package org.example.Model.OldModels;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions_audit")
public class TransactionLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int senderId;
    private String senderNickname;
    private int amountSent;
    private int receiverId;
    private String receiverNickname;
    private String time;

    public TransactionLedger() {}


    public long getId() {
        return id;
    }

    public int getSender_id() {
        return senderId;
    }
    public void setSenderId(int senderId){ this.senderId = senderId;}

    public String getSender_nickname() {
        return senderNickname;
    }
    public void setSenderNickname(String senderNickname){ this.senderNickname = senderNickname;}

    public int getAmount_sent() {
        return amountSent;
    }
    public void setAmountSent(int amountSent) { this.amountSent = amountSent;}

    public int getReceiver_id() {
        return receiverId;
    }
    public void setReceiverId(int receiverId) {this.receiverId = receiverId;}

    public String getReceiver_nickname() {
        return receiverNickname;
    }
    public void setReceiverNickname(String receiverNickname){ this.receiverNickname = receiverNickname;}

    public String getTime() {
        return time;
    }
}
