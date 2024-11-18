package org.niklasv1.banking.deposit;

import java.util.UUID;

public record DepositResponseData(UUID depositId, UUID accountId, Long amount, String timestamp) {
}
