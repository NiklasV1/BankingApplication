package org.niklasv1.banking.withdrawal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            throw new IllegalStateException("Balance too low!");
        }
        Withdrawal withdrawal = new Withdrawal(account, amount);
        withdrawalRepository.persist(withdrawal);
        account.setBalance(previousBalance - amount);
        return withdrawal.getId();
    }

    public List<WithdrawalResponseData> viewWithdrawal(Account account) {
        List<Withdrawal> withdrawals = account.getWithdrawals();
        List<WithdrawalResponseData> responseData = new ArrayList<>();

        for (Withdrawal withdrawal : withdrawals) {
            responseData.add(new WithdrawalResponseData(
                    withdrawal.getId(),
                    withdrawal.getAccount().getId(),
                    withdrawal.getAmount(),
                    withdrawal.getTimestamp().format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"))
            ));
        }

        return responseData;
    }
}
