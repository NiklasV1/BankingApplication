package org.niklasv1.banking;

public class InputValidator {

    public static void checkString(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cant be empty: " + input);
        }
    }

    public static void checkString(String input, int max_length) {
        checkString(input);
        if (input.length() > max_length) {
            throw new IllegalArgumentException("Input too long: " + input);
        }
    }

    public static void checkAuthData(AuthData authData) {
        checkString(authData.username(), 50);
        checkString(authData.plainPassword());
    }

    public static void checkAccountAuthData(AccountAuthData accountAuthData) {
        AuthData authData = new AuthData(
                accountAuthData.id(),
                accountAuthData.username(),
                accountAuthData.plainPassword()
        );
        checkAuthData(authData);
    }
}
