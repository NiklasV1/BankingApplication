package org.niklasv1.banking.deposit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class DepositController {

    @Inject
    DepositRepository depositRepository;
}
