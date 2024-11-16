package org.niklasv1.banking.transaction;

import jakarta.persistence.*;
import org.niklasv1.banking.account.Account;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver", nullable = false)
    private Account receiver;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Transaction(String message, Long amount, Account sender, Account receiver) {
        this.message = message;
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
