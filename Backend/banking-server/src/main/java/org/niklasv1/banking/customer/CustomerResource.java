package org.niklasv1.banking.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.niklasv1.banking.AuthData;
import org.niklasv1.banking.BankController;
import org.niklasv1.banking.HashGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Path("/api/customer")
@ApplicationScoped
public class CustomerResource {

    @Inject
    BankController bankController;

    @GET
    @Path("/all")
    public List<Customer> getAllCustomers() {
        return bankController.getAllCustomers();
    }

    @POST
    @Path("/login")
    public UUID loginCustomer(AuthData authData) {
        // TODO input validation + Error handling
        return bankController.loginCustomer(authData);
    }

    @POST
    @Path("/register")
    public UUID registerCustomer(CustomerFormData customerFormData) {
        // TODO input validation + Error handling
        byte[] hashedPassword = HashGenerator.sha256(customerFormData.plainPassword());

        System.out.println(Arrays.toString(hashedPassword));
        Customer customer = new Customer(customerFormData.firstName(),
                customerFormData.lastName(),
                customerFormData.address(),
                customerFormData.username(),
                hashedPassword);

        return bankController.registerCustomer(customer);
    }
}
