package org.niklasv1.banking.deposit;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.AuthData;
import org.niklasv1.banking.account.Account;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class DepositController {

    @Inject
    DepositRepository depositRepository;

    public UUID makeDeposit(Account account, Long amount) {
        if (account.isFrozen()) {
            throw new IllegalStateException("Account is frozen!");
        }
        Long previousBalance = account.getBalance();
        Deposit deposit = new Deposit(account, amount);
        depositRepository.persist(deposit);
        account.setBalance(previousBalance + amount);
        return deposit.getId();
    }

    public List<Deposit> viewDeposits(Account account) {
        PanacheQuery<Deposit> query = depositRepository.find("account", account);
        return query.list();
    }
}
