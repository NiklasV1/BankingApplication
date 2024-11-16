package org.niklasv1.banking.transaction;

import java.util.UUID;

public record TransactionData(UUID id, String username, String plainPassword, UUID accountId, UUID receiverId, String message, Long amount) {
}
