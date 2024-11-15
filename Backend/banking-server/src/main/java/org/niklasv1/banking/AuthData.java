package org.niklasv1.banking;

import java.util.UUID;

public record AuthData(UUID id, String username, String plainPassword) {
}
