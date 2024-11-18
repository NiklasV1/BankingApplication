package org.niklasv1.banking.deposit;

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

    public List<DepositResponseData> viewDeposits(Account account) {
        List<Deposit> deposits = account.getDeposits();
        List<DepositResponseData> responseData = new ArrayList<>();

        for (Deposit deposit : deposits) {
            responseData.add(new DepositResponseData(
                    deposit.getId(),
                    deposit.getAccount().getId(),
                    deposit.getAmount(),
                    deposit.getTimestamp().format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"))
            ));
        }

        return responseData;
    }
}
