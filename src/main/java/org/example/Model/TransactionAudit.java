package org.example.Model;

public record TransactionAudit(int id, int senderId, String senderName, int moneyInCents, int getterId, String getterName, String time) {


}

// money in cents