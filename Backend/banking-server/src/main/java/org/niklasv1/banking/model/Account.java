package org.niklasv1.banking.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    public UUID id;

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    public Customer owner;

    @Column(nullable = false, length = 100)
    public String name;

    @Column(nullable = false)
    public Long balance;

    @Column(nullable = false)
    public boolean frozen;
}
