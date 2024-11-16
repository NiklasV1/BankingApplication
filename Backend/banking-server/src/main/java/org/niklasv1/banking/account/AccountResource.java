package org.niklasv1.banking.account;

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

@Path("/api/account")
@ApplicationScoped
public class AccountResource {

    @Inject
    BankController bankController;

    @GET
    @Path("/all")
    public List<Account> getAllAccounts() {
        return bankController.getAllAccounts();
    }

    @POST
    @Path("/create")
    public UUID createAccount(AccountCreateData accountCreateData) {
        // TODO input validation + Error handling
        AuthData authData = new AuthData(
                accountCreateData.id(),
                accountCreateData.username(),
                accountCreateData.plainPassword()
        );

        return bankController.createAccount(authData, accountCreateData.name());
    }

    @POST
    @Path("/delete")
    public UUID deleteAccount(AccountAuthData accountAuthData) {
        // TODO input validation + Error handling

        return bankController.deleteAccount(accountAuthData);
    }

    @GET
    @Path("/view")
    public List<Account> viewAccounts(AuthData authData) {
        // TODO input validation + Error handling
        return bankController.viewAccounts(authData);
    }

    @POST
    @Path("/freeze")
    public String freezeAccount(AccountAuthData accountAuthData) {
        // TODO input validation + Error handling

        return bankController.freezeAccount(accountAuthData);
    }

    @POST
    @Path("/unfreeze")
    public UUID unfreezeAccount(AccountUnfreezeData accountUnfreezeData) {
        // TODO input validation + Error handling
        AccountAuthData accountAuthData = new AccountAuthData(
                accountUnfreezeData.id(),
                accountUnfreezeData.username(),
                accountUnfreezeData.plainPassword(),
                accountUnfreezeData.accountId()
        );

        return bankController.unfreezeAccount(accountAuthData, accountUnfreezeData.unfreezeCode());
    }
}
