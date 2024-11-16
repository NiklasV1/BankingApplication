package org.niklasv1.banking.customer;

import java.util.UUID;

public record CustomerResponseData(UUID customerId, String firstName, String lastName, String address, String username, String passwordHash) {
}
