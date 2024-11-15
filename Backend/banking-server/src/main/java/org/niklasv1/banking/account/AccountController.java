package org.niklasv1.banking.account;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.HashGenerator;
import org.niklasv1.banking.customer.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class AccountController {

    @Inject
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.listAll();
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.findByIdOptional(id);
    }

    public UUID createAccount(Customer customer, String name) {
        PanacheQuery<Account> query = accountRepository.find("name", name);

        if (query.count() != 0) {
            throw new IllegalArgumentException("Account already exists!");
        }

        Account account = new Account(customer, name);
        accountRepository.persist(account);
        return account.getId();
    }

    public UUID deleteAccount(Account account) {
        if (account.getBalance() != 0L) {
            throw new IllegalStateException("Account balance is not zero!");
        }
        UUID deletedId = account.getId();
        accountRepository.delete(account);
        return deletedId;
    }

    public List<Account> viewAccounts(Customer customer) {
        PanacheQuery<Account> query = accountRepository.find("owner", customer);
        return query.list();
    }

    public String freezeAccount(Account account, String plainPassword) {
        if (account.isFrozen()) {
            throw new IllegalStateException("Account is already frozen!");
        }
        account.setFrozen(true);
        String rawUnfreezeCode = account.getId().toString() + plainPassword + account.getName();
        return Arrays.toString(HashGenerator.sha256(rawUnfreezeCode));
    }

    public UUID unfreezeAccount(Account account, String plainPassword, String unfreezeCode) {
        if (!account.isFrozen()) {
            throw new IllegalStateException("Account is not frozen!");
        }
        String rawUnfreezeCode = account.getId().toString() + plainPassword + account.getName();
        String realUnfreezeCode = Arrays.toString(HashGenerator.sha256(rawUnfreezeCode));

        if (!realUnfreezeCode.equals(unfreezeCode)) {
            throw new IllegalArgumentException("Wrong unfreeze code!");
        }

        account.setFrozen(false);
        return account.getId();
    }
}
