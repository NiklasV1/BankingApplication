package org.niklasv1.banking;

import java.util.UUID;

public record AccountAuthData(UUID id, String username, String plainPassword, UUID accountId) {
}
