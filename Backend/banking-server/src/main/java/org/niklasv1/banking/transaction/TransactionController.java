package org.niklasv1.banking.transaction;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TransactionController {

    @Inject
    TransactionRepository transactionRepository;
}
