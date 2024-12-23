package org.niklasv1.banking.withdrawal;

import java.util.UUID;

public record WithdrawalResponseData(UUID withdrawalId, UUID accountId, Long amount, String timestamp) {
}
