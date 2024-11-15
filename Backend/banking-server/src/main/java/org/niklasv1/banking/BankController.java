package org.niklasv1.banking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;
import org.niklasv1.banking.account.AccountController;
import org.niklasv1.banking.customer.Customer;
import org.niklasv1.banking.customer.CustomerController;
import org.niklasv1.banking.deposit.DepositController;
import org.niklasv1.banking.transaction.TransactionController;
import org.niklasv1.banking.withdrawal.WithdrawalController;

import javax.naming.AuthenticationException;
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
    public UUID createCustomer(Customer customer) {
        return customerController.createCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerController.getAllCustomers();
    }


    // Account
    public List<Account> getAllAccounts() {
        return accountController.getAllAccounts();
    }

    public UUID createAccount(AuthData authData, String name) {
        Optional<Customer> cst = customerController.getCustomerById(authData.id());
        if (cst.isEmpty()) {
            throw new IllegalArgumentException("Customer with provided ID does not exist!");
        }
        Customer customer = cst.get();

        authCheck(customer, authData);

        return accountController.createAccount(customer, name);
    }


    // Utils
    private void authCheck(Customer customer, AuthData authData){
        if (!Objects.equals(authData.username(), customer.getUsername())) {
            throw new IllegalArgumentException("Wrong username!");
        }
        if (!Arrays.equals(HashGenerator.sha256(authData.plainPassword()), customer.getPassword())) {
            throw new IllegalArgumentException("Wrong password!");
        }
    }
}
