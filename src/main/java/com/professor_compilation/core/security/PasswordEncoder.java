package com.professor_compilation.core.security;

/**
 * interface PasswordEncoder
 */
public interface PasswordEncoder {

    /**
     * Checks the entered password matches with the hashed password
     * @param plainPassword the entered plain text password
     * @param hashedPassword the stored hashed password
     * @return true if the password matches with the hash
     */
    boolean matches(String plainPassword, String hashedPassword);

    /**
     * Generate hashed password from regular string.
     * Checks the entered password matches with the hashed password
     * @param password the entered text password
     * @return hashed password
     */
    String encode(String password);

}

