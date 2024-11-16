package org.niklasv1.banking.account;

import jakarta.persistence.*;
import org.niklasv1.banking.customer.Customer;
import org.niklasv1.banking.deposit.Deposit;
import org.niklasv1.banking.transaction.Transaction;
import org.niklasv1.banking.withdrawal.Withdrawal;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "account")
    private List<Deposit> deposits;

    @OneToMany(mappedBy = "account")
    private List<Withdrawal> withdrawals;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> receivedTransactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false)
    private Customer owner;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private boolean frozen;

    public Account() {
    }

    public Account(Customer owner, String name) {
        this.owner = owner;
        this.name = name;
        balance = 0L;
        frozen = false;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
