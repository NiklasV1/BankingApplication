package org.niklasv1.banking.transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponseData(UUID transactionId, String message, Long amount, UUID senderId, UUID receiverId, String timestamp) {
}
