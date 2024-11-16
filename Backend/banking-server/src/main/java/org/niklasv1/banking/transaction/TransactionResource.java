package org.niklasv1.banking.transaction;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.AccountAuthData;
import org.niklasv1.banking.BankController;

import java.util.List;
import java.util.UUID;

@Path("/api/transaction")
@ApplicationScoped
public class TransactionResource {

    @Inject
    BankController bankController;

    @POST
    @Path("/make")
    public UUID makeTransaction(TransactionData transactionData) {
        // TODO input validation + Error handling
        AccountAuthData accountAuthData = new AccountAuthData(
                transactionData.id(),
                transactionData.username(),
                transactionData.plainPassword(),
                transactionData.accountId()
        );

        return bankController.makeTransaction(accountAuthData, transactionData.receiverId(), transactionData.message(), transactionData.amount());
    }

    @POST
    @Path("/view/sent")
    public List<Transaction> viewSentTransactions(AccountAuthData accountAuthData) {
        // TODO input validation + Error handling

        return bankController.viewSentTransactions(accountAuthData);
    }

    @POST
    @Path("/view/received")
    public List<Transaction> viewReceivedTransactions(AccountAuthData accountAuthData) {
        // TODO input validation + Error handling

        return bankController.viewReceivedTransactions(accountAuthData);
    }
}
