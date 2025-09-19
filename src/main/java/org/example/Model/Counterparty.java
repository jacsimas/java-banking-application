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

    public UUID getCounterparty_id() {
        return counterparty_id;
    }

    public void setCounterparty_id(UUID counterparty_id) {
        this.counterparty_id = counterparty_id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public UUID getTarget_account_id() {
        return target_account_id;
    }

    public void setTarget_account_id(UUID target_account_id) {
        this.target_account_id = target_account_id;
    }

    public String getExternal_iban() {
        return external_iban;
    }

    public void setExternal_iban(String external_iban) {
        this.external_iban = external_iban;
    }

    public String getExternal_bic() {
        return external_bic;
    }

    public void setExternal_bic(String external_bic) {
        this.external_bic = external_bic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
