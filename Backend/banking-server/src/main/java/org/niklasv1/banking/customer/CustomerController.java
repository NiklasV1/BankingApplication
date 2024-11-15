package org.niklasv1.banking.customer;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.niklasv1.banking.AuthData;

import java.util.*;

@ApplicationScoped
@Transactional
public class CustomerController {

    @Inject
    CustomerRepository customerRepository;

    public UUID loginCustomer(String username, byte[] password) {
        PanacheQuery<Customer> query = customerRepository.find("username = :usr and password = :pw", Parameters.with("usr", username).and("pw", password));
        try {
            Customer customer = query.firstResult();
            return customer.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Wrong combination of username and password!");
        }
    }

    public UUID registerCustomer(Customer customer) {
        PanacheQuery<Customer> query = customerRepository.find("username", customer.getUsername());

        if (query.count() != 0) {
            throw new IllegalArgumentException("Account with the same name already exists!");
        }
        customerRepository.persist(customer);
        return customer.getId();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.listAll();
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findByIdOptional(id);
    }

}
