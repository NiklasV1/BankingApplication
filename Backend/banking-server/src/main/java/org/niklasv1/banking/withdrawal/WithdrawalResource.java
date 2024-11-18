package org.niklasv1.banking.withdrawal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.AccountAuthData;
import org.niklasv1.banking.AuthData;
import org.niklasv1.banking.BankController;
import org.niklasv1.banking.InputValidator;
import org.niklasv1.banking.deposit.Deposit;

import java.util.List;
import java.util.UUID;

@Path("/api/withdrawal")
@ApplicationScoped
public class WithdrawalResource {

    @Inject
    BankController bankController;

    @POST
    @Path("/make")
    public UUID makeWithdrawal(WithdrawalData withdrawalData) {
        InputValidator.checkString(withdrawalData.username(),50);
        InputValidator.checkString(withdrawalData.plainPassword());
        AccountAuthData accountAuthData = new AccountAuthData(
                withdrawalData.id(),
                withdrawalData.username(),
                withdrawalData.plainPassword(),
                withdrawalData.accountId()
        );

        return bankController.makeWithdrawal(accountAuthData, withdrawalData.amount());
    }

    @POST
    @Path("/view")
    public List<WithdrawalResponseData> viewWithdrawals(AccountAuthData accountAuthData) {
        InputValidator.checkAccountAuthData(accountAuthData);
        return bankController.viewWithdrawals(accountAuthData);
    }
}
