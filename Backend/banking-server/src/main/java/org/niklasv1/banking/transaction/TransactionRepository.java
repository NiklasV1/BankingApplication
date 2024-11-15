package org.niklasv1.banking.transaction;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<Transaction, UUID> {
}
