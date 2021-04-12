package com.alexis.students.service.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Encryption {

    private static final Random RANDOM = new SecureRandom();

    public String generateHash(String sid) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        final byte[] hashbytes = digest.digest(sid.getBytes(StandardCharsets.UTF_8));
        final byte[] salt = getNextSalt();

        // combine hash and salt
        byte[] combined = new byte[hashbytes.length + salt.length];
        for (int i = 0; i < combined.length; ++i) {
            combined[i] = i < hashbytes.length ? hashbytes[i] : salt[i - hashbytes.length];
        }

        String encrypted = bytesToHex(combined);
        return encrypted;
    }

    // generate random salt
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    // convert bytes to string
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
}
