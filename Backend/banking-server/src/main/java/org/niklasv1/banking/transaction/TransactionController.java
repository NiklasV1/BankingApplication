package org.niklasv1.banking.transaction;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class TransactionController {

    @Inject
    TransactionRepository transactionRepository;

    public UUID makeTransaction(Account sender, Account receiver, String message, Long amount) {
        if (sender.isFrozen()) {
            throw new IllegalStateException("Sender account is frozen!");
        }
        if (receiver.isFrozen()) {
            throw new IllegalStateException("Receiver account is frozen!");
        }
        Long previousSenderBalance = sender.getBalance();
        Long previousReceiverBalance = sender.getBalance();
        if ((previousSenderBalance - amount) < 0) {
            throw new IllegalArgumentException("Insufficient sender balance!");
        }

        Transaction transaction = new Transaction(message, amount, sender, receiver);
        sender.setBalance(previousSenderBalance - amount);
        receiver.setBalance(previousReceiverBalance + amount);
        transactionRepository.persist(transaction);
        return transaction.getId();
    }

    public List<Transaction> viewSentTransactions(Account account) {
        PanacheQuery<Transaction> query = transactionRepository.find("sender", account);
        return query.list();
    }

    public List<Transaction> viewReceivedTransactions(Account account) {
        PanacheQuery<Transaction> query = transactionRepository.find("receiver", account);
        return query.list();
    }
}
