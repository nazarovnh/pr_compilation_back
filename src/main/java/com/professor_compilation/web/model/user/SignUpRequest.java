package com.professor_compilation.web.model.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class SignUpRequest
 */
public class SignUpRequest {
    private final String email;
    private final String password;

    /**
     * public constructor of SignUpRequest
     * @param login - login of user
     * @param password - password of user
     */
    @JsonCreator
    public SignUpRequest(
            @JsonProperty("email") final String login,
            @JsonProperty("password") final String password
    ) {
        this.email = login;
        this.password = password;
    }

    /**
     * Get email of user
     * @return email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get password of user
     * @return password of user
     */
    public String getPassword() {
        return password;
    }
}


