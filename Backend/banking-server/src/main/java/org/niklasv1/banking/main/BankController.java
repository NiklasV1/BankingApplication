package org.niklasv1.banking.main;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.AccountRepository;
import org.niklasv1.banking.customer.CustomerRepository;
import org.niklasv1.banking.deposit.DepositRepository;
import org.niklasv1.banking.transaction.TransactionRepository;
import org.niklasv1.banking.withdrawal.WithdrawalRepository;

@ApplicationScoped
@Transactional
public class BankController {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    DepositRepository depositRepository;

    @Inject
    WithdrawalRepository withdrawalRepository;


}
