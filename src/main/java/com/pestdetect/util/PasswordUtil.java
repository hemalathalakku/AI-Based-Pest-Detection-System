package com.pestdetect.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Hashes passwords with SHA-256 so we never store plain text.
 * (For a production system prefer BCrypt; SHA-256 keeps this project
 *  dependency-free and easy to understand.)
 */
public class PasswordUtil {
    public static String hash(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plain.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Unable to hash password", e);
        }
    }
}
