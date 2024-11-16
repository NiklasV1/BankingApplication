package org.niklasv1.banking.withdrawal;

import java.util.UUID;

public record WithdrawalData(UUID id, String username, String plainPassword, UUID accountId, Long amount) {
}
