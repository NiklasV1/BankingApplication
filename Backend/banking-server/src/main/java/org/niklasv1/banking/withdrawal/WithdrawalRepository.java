package org.niklasv1.banking.withdrawal;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class WithdrawalRepository implements PanacheRepositoryBase<Withdrawal, UUID> {
}
