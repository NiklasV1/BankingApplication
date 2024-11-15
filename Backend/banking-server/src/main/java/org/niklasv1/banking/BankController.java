package org.niklasv1.banking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;
import org.niklasv1.banking.account.AccountController;
import org.niklasv1.banking.account.AccountIdFormData;
import org.niklasv1.banking.customer.Customer;
import org.niklasv1.banking.customer.CustomerController;
import org.niklasv1.banking.deposit.Deposit;
import org.niklasv1.banking.deposit.DepositController;
import org.niklasv1.banking.transaction.Transaction;
import org.niklasv1.banking.transaction.TransactionController;
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

    public UUID deleteAccount(AuthData authData, UUID accountId) {
        Account account = authenticateAccount(authData, accountId);
        return accountController.deleteAccount(account);
    }

    // TODO
    public List<Account> viewAccounts(AuthData authData) {
        Customer customer = authenticateCustomer(authData);
        return accountController.viewAccounts(customer);
    }

    // TODO
    public String freezeAccount(AuthData authData, UUID account_id) {
        return null;
    }

    // TODO
    public UUID unfreezeAccount(AuthData authData, UUID account_id, String unfreezeCode) {
        return null;
    }


    // Deposit
    // TODO
    public UUID makeDeposit(AuthData authData, UUID account_id, Long amount) {
        return null;
    }

    // TODO
    public List<Deposit> viewDeposits(AuthData authData) {
        return null;
    }


    // Withdrawal
    // TODO
    public UUID makeWithdrawal(AuthData authData, UUID account_id, Long amount) {
        return null;
    }

    // TODO
    public List<Deposit> viewWithdrawals(AuthData authData) {
        return null;
    }


    // Transaction
    // TODO
    public UUID makeTransaction(AuthData authData, UUID sender_id, UUID receiver_id, String message, Long amount) {
        return null;
    }

    // TODO
    public List<Transaction> viewSentTransactions(AuthData authData, UUID account_id) {
        return null;
    }

    // TODO
    public List<Transaction> viewReceivedTransactions(AuthData authData, UUID account_id) {
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

    private Account authenticateAccount(AuthData authData, UUID accountId) {
        Customer customer = authenticateCustomer(authData);
        Optional<Account> acc = accountController.getAccountById(accountId);
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
