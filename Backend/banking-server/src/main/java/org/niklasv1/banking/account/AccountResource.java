package org.niklasv1.banking.account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

    public UUID deleteAccount(AccountIdData accountIdData) {
        // TODO input validation + Error handling
        AuthData authData = new AuthData(
                accountIdData.id(),
                accountIdData.username(),
                accountIdData.plainPassword()
        );

        return bankController.deleteAccount(authData, accountIdData.accountId());
    }

    public List<Account> viewAccounts(AuthData authData) {
        // TODO input validation + Error handling
        return bankController.viewAccounts(authData);
    }

    // TODO
    public String freezeAccount(AuthData authData, UUID account_id) {
        return null;
    }

    // TODO
    public UUID unfreezeAccount(AuthData authData, UUID account_id, String unfreezeCode) {
        return null;
    }
}
