package org.niklasv1.banking.account;

import java.util.UUID;

public record AccountCreateFormData(UUID id, String username, String plainPassword, String name) {
}
