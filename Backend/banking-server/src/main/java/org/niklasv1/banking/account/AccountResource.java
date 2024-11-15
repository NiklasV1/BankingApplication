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
    public UUID createAccount(AccountCreateFormData accountCreateFormData) {
        // TODO input validation + Error handling
        AuthData authData = new AuthData(
                accountCreateFormData.id(),
                accountCreateFormData.username(),
                accountCreateFormData.plainPassword()
        );

        return bankController.createAccount(authData, accountCreateFormData.name());
    }

    public UUID deleteAccount(AccountIdFormData accountIdFormData) {
        // TODO input validation + Error handling
        AuthData authData = new AuthData(
                accountIdFormData.id(),
                accountIdFormData.username(),
                accountIdFormData.plainPassword()
        );

        return bankController.deleteAccount(authData, accountIdFormData.accountId());
    }

    // TODO
    public List<Account> viewAccounts(AuthData authData) {
        return null;
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
