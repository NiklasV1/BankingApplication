package org.niklasv1.banking.customer;

public record CustomerFormData(String firstName, String lastName, String address, String username,
                               String plainPassword) {
}
