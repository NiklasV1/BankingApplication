package org.niklasv1.banking;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    public static byte[] sha256(String input) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return  digest.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
