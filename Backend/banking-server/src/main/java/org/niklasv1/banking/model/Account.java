package org.niklasv1.banking.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner")
    public Customer owner;

    @OneToMany(mappedBy = "sender")
    public List<Transaction> transactionsSent;

    @OneToMany(mappedBy = "receiver")
    public List<Transaction> transactionsReceived;

    @OneToMany(mappedBy = "account")
    public List<Withdrawal> withdrawals;

    @OneToMany(mappedBy = "account")
    public List<Deposit> deposits;

    @Column(nullable = false, length = 100)
    public String name;

    @Column(nullable = false)
    public Long balance;

    @Column(nullable = false)
    public boolean frozen;
}
