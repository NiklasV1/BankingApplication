package org.niklasv1.banking.account;

import java.util.UUID;

public record AccountResponseData(UUID accountId, UUID ownerId, String name, Long balance, boolean frozen) {
}
