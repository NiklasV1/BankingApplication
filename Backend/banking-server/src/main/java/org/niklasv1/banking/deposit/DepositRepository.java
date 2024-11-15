package org.niklasv1.banking.deposit;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class DepositRepository implements PanacheRepositoryBase<Deposit, UUID> {
}
