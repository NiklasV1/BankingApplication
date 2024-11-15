package org.niklasv1.banking.endpoints;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.logic.BankController;

@Path("/api/customer")
@ApplicationScoped
public class CustomerResource {

    @Inject
    BankController bankController;

    @GET
    public String test() {
        return "customer";
    }
}
