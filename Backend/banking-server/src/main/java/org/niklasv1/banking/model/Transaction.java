package org.niklasv1.banking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue
    public UUID id;

    @Column(nullable = false)
    public String message;

    @Column(nullable = false)
    public Long amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender")
    public Account sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver")
    public Account receiver;

    @Column(nullable = false)
    public LocalDateTime timestamp;
}
