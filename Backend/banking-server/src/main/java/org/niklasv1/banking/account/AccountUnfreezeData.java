package org.niklasv1.banking.account;

import java.util.UUID;

public record AccountUnfreezeData(UUID id, String username, String plainPassword, UUID accountId, String unfreezeCode) {
}
