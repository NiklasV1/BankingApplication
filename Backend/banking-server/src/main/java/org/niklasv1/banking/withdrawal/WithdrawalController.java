package org.niklasv1.banking.withdrawal;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;
import org.niklasv1.banking.deposit.Deposit;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class WithdrawalController {

    @Inject
    WithdrawalRepository withdrawalRepository;

    public UUID makeWithdrawal(Account account, Long amount) {
        if (account.isFrozen()) {
            throw new IllegalStateException("Account is frozen!");
        }
        Long previousBalance = account.getBalance();
        if ((previousBalance - amount) < 0L) {
            throw new IllegalStateException("Balance to low!");
        }
        Withdrawal withdrawal = new Withdrawal(account, amount);
        withdrawalRepository.persist(withdrawal);
        account.setBalance(previousBalance - amount);
        return withdrawal.getId();
    }

    public List<Withdrawal> viewWithdrawal(Account account) {
        PanacheQuery<Withdrawal> query = withdrawalRepository.find("account", account);
        return query.list();
    }
}
