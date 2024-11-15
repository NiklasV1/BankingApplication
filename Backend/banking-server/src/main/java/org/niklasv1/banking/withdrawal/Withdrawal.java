package org.niklasv1.banking.withdrawal;

import jakarta.persistence.*;
import org.niklasv1.banking.account.Account;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "withdrawals")
public class Withdrawal {

    @Id
    @GeneratedValue
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account")
    public Account account;

    @Column(nullable = false)
    public Long amount;

    @Column(nullable = false)
    public LocalDateTime timestamp;
}
