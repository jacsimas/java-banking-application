package org.example.ModelDTO.OldDTO;

public class RequestTransactionLedgerDTO {

    int senderId;
    String senderNickname;
    int amountSent;
    int receiverId;
    String receiverNickname;

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(int amountSent) {
        this.amountSent = amountSent;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
    }
}
