package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private String kycStatus;

    public UUID getid() {
        return id;
    }

    public void setid(UUID id) {
        this.id = id;
    }

    public String getkycStatus() {
        return kycStatus;
    }

    public void setkycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
