package org.niklasv1.banking.account;

import java.util.UUID;

public record AccountIdFormData(UUID id, String username, String plainPassword, UUID accountId) {
}
