package org.niklasv1.banking.account;

import java.util.UUID;

public record AccountFormData(UUID id, String username, String plainPassword, String name) {
}
