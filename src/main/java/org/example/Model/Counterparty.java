package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "counterparty")
public class Counterparty {

    @Id
    private UUID counterparty_id;
    private UUID customer_id;
    private String display_name;
    private UUID target_account_id;
    private String external_iban;
    private String external_bic;
    private String tags;
    private String created_at;

}
