package org.niklasv1.banking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.AccountController;
import org.niklasv1.banking.customer.CustomerController;
import org.niklasv1.banking.deposit.DepositController;
import org.niklasv1.banking.transaction.TransactionController;
import org.niklasv1.banking.withdrawal.WithdrawalController;

@ApplicationScoped
@Transactional
public class BankController {

    @Inject
    AccountController accountController;

    @Inject
    CustomerController customerController;

    @Inject
    TransactionController transactionController;

    @Inject
    DepositController depositController;

    @Inject
    WithdrawalController withdrawalController;


}
