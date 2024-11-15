package org.niklasv1.banking.account;

import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.customer.Customer;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class AccountController {

    @Inject
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.listAll();
    }

    public UUID createAccount(Customer customer, String name) {
        Account account = new Account(customer, name);
        accountRepository.persist(account);
        return account.getId();
    }
}
