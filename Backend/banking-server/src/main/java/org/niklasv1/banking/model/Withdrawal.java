package org.niklasv1.banking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "withdrawals")
public class Withdrawal {

    @Id
    @GeneratedValue
    public UUID id;

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    public Account account;

    @Column(nullable = false)
    public Long amount;

    @Column(nullable = false)
    public LocalDateTime timestamp;
}
