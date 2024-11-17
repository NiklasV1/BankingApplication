package org.niklasv1.banking.account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.HashGenerator;
import org.niklasv1.banking.customer.Customer;

import java.util.*;

@ApplicationScoped
@Transactional
public class AccountController {

    @Inject
    AccountRepository accountRepository;

    public List<AccountResponseData> getAllAccounts() {
        List<Account> accounts = accountRepository.listAll();
        List<AccountResponseData> responseData = new ArrayList<>();

        for (Account account : accounts) {
            responseData.add(new AccountResponseData(
                    account.getId(),
                    account.getOwner().getId(),
                    account.getName(),
                    account.getBalance(),
                    account.isFrozen()
            ));
        }

        return responseData;
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.findByIdOptional(id);
    }

    public UUID createAccount(Customer customer, String name) {
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

    public List<AccountResponseData> viewAccounts(Customer customer) {
        List<Account> accounts = customer.getAccounts();
        List<AccountResponseData> responseData = new ArrayList<>();

        for (Account account : accounts) {
            responseData.add(new AccountResponseData(
                    account.getId(),
                    account.getOwner().getId(),
                    account.getName(),
                    account.getBalance(),
                    account.isFrozen()
            ));
        }

        return responseData;
    }

    public String freezeAccount(Account account, String plainPassword) {
        if (account.isFrozen()) {
            throw new IllegalStateException("Account is already frozen!");
        }
        account.setFrozen(true);
        String rawUnfreezeCode = account.getId().toString() + plainPassword + account.getName();
        byte[] rawUnfreezeCode2 = HashGenerator.sha256(rawUnfreezeCode);
        StringBuilder realUnfreezeCode = new StringBuilder();
        for (byte b : rawUnfreezeCode2) {
            realUnfreezeCode.append(String.format("%02x", b));
        }

        return "UNFREEZE CODE (for manual unfreeze): " + realUnfreezeCode.toString();
    }

    public UUID unfreezeAccount(Account account, String plainPassword, String unfreezeCode) {
        if (!account.isFrozen()) {
            throw new IllegalStateException("Account is not frozen!");
        }
        String rawUnfreezeCode = account.getId().toString() + plainPassword + account.getName();
        byte[] rawUnfreezeCode2 = HashGenerator.sha256(rawUnfreezeCode);
        StringBuilder realUnfreezeCode = new StringBuilder();
        for (byte b : rawUnfreezeCode2) {
            realUnfreezeCode.append(String.format("%02x", b));
        }

        if (!realUnfreezeCode.toString().equals(unfreezeCode)) {
            throw new IllegalArgumentException("Wrong unfreeze code!");
        }

        account.setFrozen(false);
        return account.getId();
    }
}
