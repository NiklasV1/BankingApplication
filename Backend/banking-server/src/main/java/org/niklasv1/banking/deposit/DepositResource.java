package org.niklasv1.banking.deposit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.BankController;

@Path("/api/deposit")
@ApplicationScoped
public class DepositResource {

    @Inject
    BankController bankController;

    @GET
    public String test() {
        return "deposit";
    }
}
