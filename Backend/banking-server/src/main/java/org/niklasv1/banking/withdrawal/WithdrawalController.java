package org.niklasv1.banking.withdrawal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class WithdrawalController {

    @Inject
    WithdrawalRepository withdrawalRepository;
}
