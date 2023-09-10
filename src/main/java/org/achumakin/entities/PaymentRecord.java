package org.achumakin.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class PaymentRecord extends PanacheEntity {

    private Double amount;
    private String currency;
    private String name;

    public PaymentRecord() {
    }

    public Double getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getName() {
        return this.name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setName(String name) {
        this.name = name;
    }
}
