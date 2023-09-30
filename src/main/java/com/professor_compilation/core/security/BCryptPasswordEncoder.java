package com.professor_compilation.core.security;


import org.mindrot.jbcrypt.BCrypt;

/**
 * class BCryptPasswordEncoder
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

    public boolean matches(final String plainPassword, final String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public String encode(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}

