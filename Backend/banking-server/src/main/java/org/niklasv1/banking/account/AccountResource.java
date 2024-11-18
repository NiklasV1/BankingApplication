package org.niklasv1.banking.account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.AccountAuthData;
import org.niklasv1.banking.AuthData;
import org.niklasv1.banking.BankController;
import org.niklasv1.banking.InputValidator;

import java.util.List;
import java.util.UUID;

@Path("/api/account")
@ApplicationScoped
public class AccountResource {

    @Inject
    BankController bankController;

    @GET
    @Path("/all")
    public List<AccountResponseData> getAllAccounts() {
        return bankController.getAllAccounts();
    }

    @POST
    @Path("/create")
    public UUID createAccount(AccountCreateData accountCreateData) {
        InputValidator.checkString(accountCreateData.username(), 50);
        InputValidator.checkString(accountCreateData.plainPassword());
        InputValidator.checkString(accountCreateData.name(), 100);

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
        InputValidator.checkAccountAuthData(accountAuthData);

        return bankController.deleteAccount(accountAuthData);
    }

    @POST
    @Path("/view")
    public List<AccountResponseData> viewAccounts(AuthData authData) {
        InputValidator.checkAuthData(authData);
        return bankController.viewAccounts(authData);
    }

    @POST
    @Path("/freeze")
    public String freezeAccount(AccountAuthData accountAuthData) {
        InputValidator.checkAccountAuthData(accountAuthData);
        return bankController.freezeAccount(accountAuthData);
    }

    @POST
    @Path("/unfreeze")
    public UUID unfreezeAccount(AccountUnfreezeData accountUnfreezeData) {
        AccountAuthData accountAuthData = new AccountAuthData(
                accountUnfreezeData.id(),
                accountUnfreezeData.username(),
                accountUnfreezeData.plainPassword(),
                accountUnfreezeData.accountId()
        );

        InputValidator.checkAccountAuthData(accountAuthData);
        InputValidator.checkString(accountUnfreezeData.unfreezeCode());

        return bankController.unfreezeAccount(accountAuthData, accountUnfreezeData.unfreezeCode());
    }
}
