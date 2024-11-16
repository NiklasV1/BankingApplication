package org.niklasv1.banking.withdrawal;

import java.time.LocalDateTime;
import java.util.UUID;

public record WithdrawalResponseData(UUID withdrawalId, UUID accountId, Long amount, LocalDateTime timestamp) {
}
