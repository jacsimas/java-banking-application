package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private UUID customer_id;
    private String full_name;
    private String email;
    private String phone;
    private String kyc_status;

}
