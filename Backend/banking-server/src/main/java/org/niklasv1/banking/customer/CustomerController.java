package org.niklasv1.banking.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CustomerController {

    @Inject
    CustomerRepository customerRepository;
}
