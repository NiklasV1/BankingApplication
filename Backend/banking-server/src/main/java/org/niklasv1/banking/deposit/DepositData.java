package org.niklasv1.banking.deposit;

import java.util.UUID;

public record DepositData(UUID id, String username, String plainPassword, UUID accountId, Long amount) {
}
