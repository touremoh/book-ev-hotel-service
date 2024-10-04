package com.bookevhotel.core.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
@UtilityClass
public class SecretGenerator {

    // Method to generate a random secret using Base64 encoding
    public String generateSecret(int length) {
        // Create a secure random generator
        SecureRandom random = new SecureRandom();
        
        // Create a byte array for the secret
        byte[] secretBytes = new byte[length];
        
        // Generate random bytes
        random.nextBytes(secretBytes);
        
        // Encode the secret bytes in Base64
        return Base64.getEncoder().encodeToString(secretBytes);
    }
}
