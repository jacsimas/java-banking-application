package org.example.Model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ledger_entry")
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long entry_id;
    private UUID transaction_id;
    private UUID account_id;
    private String currency;
    private int amount;
    private String side;
    private String event_time;
    private String description;


}
