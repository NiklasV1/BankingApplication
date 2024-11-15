package org.niklasv1.banking.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.model.*;

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
