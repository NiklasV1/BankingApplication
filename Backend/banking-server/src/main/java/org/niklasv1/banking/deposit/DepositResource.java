package org.niklasv1.banking.deposit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.AccountAuthData;
import org.niklasv1.banking.AuthData;
import org.niklasv1.banking.BankController;

import java.util.List;
import java.util.UUID;

@Path("/api/deposit")
@ApplicationScoped
public class DepositResource {

    @Inject
    BankController bankController;

    @POST
    @Path("/make")
    public UUID makeDeposit(DepositData depositData) {
        // TODO input validation + Error handling
        AccountAuthData accountAuthData = new AccountAuthData(
                depositData.id(),
                depositData.username(),
                depositData.plainPassword(),
                depositData.accountId()
        );

        return bankController.makeDeposit(accountAuthData, depositData.amount());
    }

    @POST
    @Path("/view")
    public List<DepositResponseData> viewDeposits(AccountAuthData accountAuthData) {
        // TODO input validation + Error handling

        return bankController.viewDeposits(accountAuthData);
    }
}
