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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner")
    private Customer owner;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> transactionsSent;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> transactionsReceived;

    @OneToMany(mappedBy = "account")
    private List<Withdrawal> withdrawals;

    @OneToMany(mappedBy = "account")
    private List<Deposit> deposits;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private boolean frozen;

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

    public List<Transaction> getTransactionsSent() {
        return transactionsSent;
    }

    public void setTransactionsSent(List<Transaction> transactionsSent) {
        this.transactionsSent = transactionsSent;
    }

    public List<Transaction> getTransactionsReceived() {
        return transactionsReceived;
    }

    public void setTransactionsReceived(List<Transaction> transactionsReceived) {
        this.transactionsReceived = transactionsReceived;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<Withdrawal> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
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
