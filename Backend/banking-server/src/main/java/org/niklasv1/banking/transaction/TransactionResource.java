package org.niklasv1.banking.transaction;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.AccountAuthData;
import org.niklasv1.banking.BankController;
import org.niklasv1.banking.InputValidator;

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
        InputValidator.checkString(transactionData.message(), 100);
        AccountAuthData accountAuthData = new AccountAuthData(
                transactionData.id(),
                transactionData.username(),
                transactionData.plainPassword(),
                transactionData.accountId()
        );
        InputValidator.checkAccountAuthData(accountAuthData);

        return bankController.makeTransaction(accountAuthData, transactionData.receiverId(), transactionData.message(), transactionData.amount());
    }

    @POST
    @Path("/view/sent")
    public List<TransactionResponseData> viewSentTransactions(AccountAuthData accountAuthData) {
        InputValidator.checkAccountAuthData(accountAuthData);
        return bankController.viewSentTransactions(accountAuthData);
    }

    @POST
    @Path("/view/received")
    public List<TransactionResponseData> viewReceivedTransactions(AccountAuthData accountAuthData) {
        InputValidator.checkAccountAuthData(accountAuthData);
        return bankController.viewReceivedTransactions(accountAuthData);
    }
}
