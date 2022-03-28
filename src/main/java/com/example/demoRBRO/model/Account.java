package com.example.demoRBRO.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table()
public class Account {
    private Long id;
    private String iban;
    private Long currency;
    private BigDecimal balance;
    private Date lastUpdateDate;

    public Account() {
    }

    public Account(Long id, String iban, Long currency, BigDecimal balance, Date lastUpdateDate) {
        this.id = id;
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    @Column(unique = true, nullable = false)
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Column(nullable = false)
    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    @Column(nullable = false)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Column(name="LAST_UPDATE_DATE", nullable = false)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
