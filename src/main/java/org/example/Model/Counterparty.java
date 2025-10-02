package org.example.Model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "counterparty")
public class Counterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID customerId;
    private String displayName;
    private UUID targetAccountId;
    private String externalIban;
    private String externalBic;
    private String tags;
    private String createdAt;

    public UUID getid() {
        return id;
    }

    public void setid(UUID id) {
        this.id = id;
    }

    public String getdisplayName() {
        return displayName;
    }

    public void setdisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UUID getcustomerId() {
        return customerId;
    }

    public void setcustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID gettargetAccountId() {
        return targetAccountId;
    }

    public void settargetAccountId(UUID targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public String getexternalIban() {
        return externalIban;
    }

    public void setexternalIban(String externalIban) {
        this.externalIban = externalIban;
    }

    public String getexternalBic() {
        return externalBic;
    }

    public void setexternalBic(String externalBic) {
        this.externalBic = externalBic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
