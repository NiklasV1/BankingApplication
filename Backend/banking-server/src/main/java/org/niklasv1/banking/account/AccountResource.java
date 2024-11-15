package org.niklasv1.banking.account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public String test() {
        return "account";
    }

    @GET
    @Path("/all")
    public List<Account> getAllAccounts() {
        return bankController.getAllAccounts();
    }

    @POST
    @Path("/create")
    public UUID createAccount(AccountFormData accountFormData) {

        AuthData authData = new AuthData(
                accountFormData.id(),
                accountFormData.username(),
                accountFormData.plainPassword()
        );

        return bankController.createAccount(authData, accountFormData.name());
    }
}
