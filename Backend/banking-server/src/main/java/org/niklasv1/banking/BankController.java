package org.niklasv1.banking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;
import org.niklasv1.banking.account.AccountController;
import org.niklasv1.banking.customer.Customer;
import org.niklasv1.banking.customer.CustomerController;
import org.niklasv1.banking.deposit.Deposit;
import org.niklasv1.banking.deposit.DepositController;
import org.niklasv1.banking.transaction.Transaction;
import org.niklasv1.banking.transaction.TransactionController;
import org.niklasv1.banking.withdrawal.Withdrawal;
import org.niklasv1.banking.withdrawal.WithdrawalController;

import java.util.*;

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


    // Customer
    public UUID loginCustomer(AuthData authData) {
        return customerController.loginCustomer(authData.username(), HashGenerator.sha256(authData.plainPassword()));
    }

    public UUID registerCustomer(Customer customer) {
        return customerController.registerCustomer(customer);
    }

    // TODO remove after testing
    public List<Customer> getAllCustomers() {
        return customerController.getAllCustomers();
    }


    // Account
    // TODO remove after testing
    public List<Account> getAllAccounts() {
        return accountController.getAllAccounts();
    }

    public UUID createAccount(AuthData authData, String name) {
        return accountController.createAccount(authenticateCustomer(authData), name);
    }

    public UUID deleteAccount(AccountAuthData accountAuthData) {
        Account account = authenticateAccount(accountAuthData);
        return accountController.deleteAccount(account);
    }

    public List<Account> viewAccounts(AuthData authData) {
        Customer customer = authenticateCustomer(authData);
        return accountController.viewAccounts(customer);
    }

    public String freezeAccount(AccountAuthData accountAuthData) {
        Account account = authenticateAccount(accountAuthData);
        return accountController.freezeAccount(account, accountAuthData.plainPassword());
    }

    public UUID unfreezeAccount(AccountAuthData accountAuthData, String unfreezeCode) {
        Account account = authenticateAccount(accountAuthData);
        return accountController.unfreezeAccount(account, accountAuthData.plainPassword(), unfreezeCode);
    }


    // Deposit
    public UUID makeDeposit(AccountAuthData accountAuthData, Long amount) {
        Account account = authenticateAccount(accountAuthData);
        return depositController.makeDeposit(account, amount);
    }

    public List<Deposit> viewDeposits(AccountAuthData accountAuthData) {
        Account account = authenticateAccount(accountAuthData);
        return depositController.viewDeposits(account);
    }


    // Withdrawal
    public UUID makeWithdrawal(AccountAuthData accountAuthData, Long amount) {
        Account account = authenticateAccount(accountAuthData);
        return withdrawalController.makeWithdrawal(account, amount);
    }

    public List<Withdrawal> viewWithdrawals(AccountAuthData accountAuthData) {
        Account account = authenticateAccount(accountAuthData);
        return withdrawalController.viewWithdrawal(account);
    }


    // Transaction
    // TODO
    public UUID makeTransaction(AccountAuthData accountAuthData, UUID receiverId, String message, Long amount) {
        return null;
    }

    // TODO
    public List<Transaction> viewSentTransactions(AccountAuthData accountAuthData) {
        return null;
    }

    // TODO
    public List<Transaction> viewReceivedTransactions(AccountAuthData accountAuthData) {
        return null;
    }


    // Utils
    private Customer authenticateCustomer(AuthData authData) {
        Optional<Customer> cst = customerController.getCustomerById(authData.id());
        if (cst.isEmpty()) {
            throw new IllegalArgumentException("Customer with provided ID does not exist!");
        }
        Customer customer = cst.get();
        if (!Objects.equals(authData.username(), customer.getUsername())) {
            throw new IllegalArgumentException("Wrong username!");
        }
        if (!Arrays.equals(HashGenerator.sha256(authData.plainPassword()), customer.getPassword())) {
            throw new IllegalArgumentException("Wrong password!");
        }
        return customer;
    }

    private Account authenticateAccount(AccountAuthData accountAuthData) {
        AuthData authData = new AuthData(
                accountAuthData.id(),
                accountAuthData.username(),
                accountAuthData.plainPassword()
        );

        Customer customer = authenticateCustomer(authData);
        Optional<Account> acc = accountController.getAccountById(accountAuthData.id());
        if (acc.isEmpty()) {
            throw new IllegalArgumentException("Account with provided ID does not exist!");
        }
        Account account = acc.get();
        if (!Objects.equals(customer, account.getOwner())) {
            throw new IllegalArgumentException("Wrong customer!");
        }
        return account;
    }
}
