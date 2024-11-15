package org.niklasv1.banking.customer;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.*;

@ApplicationScoped
@Transactional
public class CustomerController {

    @Inject
    CustomerRepository customerRepository;

    public UUID createCustomer(Customer customer) {
        PanacheQuery<Customer> query = customerRepository.find("username", customer.getUsername());

        if (query.count() == 0) {
            customerRepository.persist(customer);
            return customer.getId();
        } else {
            throw new IllegalArgumentException("Customer already exists!");
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.listAll();
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findByIdOptional(id);
    }

}
