package org.niklasv1.banking.account;

import java.util.UUID;

public record AccountCreateData(UUID id, String username, String plainPassword, String name) {
}
