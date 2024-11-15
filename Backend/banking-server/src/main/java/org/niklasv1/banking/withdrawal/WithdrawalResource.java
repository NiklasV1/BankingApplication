package org.niklasv1.banking.withdrawal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.BankController;

@Path("/api/withdrawal")
@ApplicationScoped
public class WithdrawalResource {

    @Inject
    BankController bankController;

    @GET
    public String test() {
        return "withdrawal";
    }
}
