package org.niklasv1.banking.endpoints;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.logic.BankController;

@Path("/api/withdrawal")
@ApplicationScoped
public class WithdrawalResource {

    @Inject
    BankController bankController;
}
