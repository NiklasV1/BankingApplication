package org.niklasv1.banking.customer;

import jakarta.persistence.*;
import org.niklasv1.banking.account.Account;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    public UUID id;

    @OneToMany(mappedBy = "owner")
    public List<Account> accounts;

    @Column(name = "first_name", nullable = false, length = 100)
    public String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    public String lastName;

    @Column(nullable = false, length = 200)
    public String address;

    @Column(nullable = false, length = 50)
    public String username;

    @Column(nullable = false, length = 32)
    public byte[] password;
}
