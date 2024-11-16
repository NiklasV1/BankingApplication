package org.niklasv1.banking.deposit;

import jakarta.persistence.*;
import org.niklasv1.banking.account.Account;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Deposit() {
    }

    public Deposit(Account account, Long amount) {
        this.account = account;
        this.amount = amount;
        timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
