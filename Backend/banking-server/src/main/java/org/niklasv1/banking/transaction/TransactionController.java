package org.niklasv1.banking.transaction;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.account.Account;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        Long previousReceiverBalance = receiver.getBalance();
        if ((previousSenderBalance - amount) < 0) {
            throw new IllegalArgumentException("Insufficient sender balance!");
        }

        Transaction transaction = new Transaction(message, amount, sender, receiver);
        sender.setBalance(previousSenderBalance - amount);
        receiver.setBalance(previousReceiverBalance + amount);
        transactionRepository.persist(transaction);
        return transaction.getId();
    }

    public List<TransactionResponseData> viewSentTransactions(Account account) {
        List<Transaction> transactions = account.getSentTransactions();
        List<TransactionResponseData> responseData = new ArrayList<>();

        for (Transaction transaction : transactions) {
            responseData.add(new TransactionResponseData(
                    transaction.getId(),
                    transaction.getMessage(),
                    transaction.getAmount(),
                    transaction.getSender().getId(),
                    transaction.getReceiver().getId(),
                    transaction.getTimestamp().format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"))
            ));
        }

        return responseData;
    }

    public List<TransactionResponseData> viewReceivedTransactions(Account account) {
        List<Transaction> transactions = account.getReceivedTransactions();
        List<TransactionResponseData> responseData = new ArrayList<>();

        for (Transaction transaction : transactions) {
            responseData.add(new TransactionResponseData(
                    transaction.getId(),
                    transaction.getMessage(),
                    transaction.getAmount(),
                    transaction.getSender().getId(),
                    transaction.getReceiver().getId(),
                    transaction.getTimestamp().format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"))
            ));
        }

        return responseData;
    }
}
