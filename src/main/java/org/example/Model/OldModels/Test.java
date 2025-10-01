package org.example.Model.OldModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "testv2")
public class Test {

    @Id
    private UUID tenant_id;

    private String company_name;

    public Test(){}

    public UUID getTenant_id() {
        return tenant_id;
    }

    public String getCompany_name() {
        return company_name;
    }

}
