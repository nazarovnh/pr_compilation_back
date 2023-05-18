package com.professor_compilation.web.model.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * class UserCredentialsImpl
 */
public class UserCredentialsImpl implements UserCredentials {


    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("email")
    private final String username;

    @JsonProperty("roles")
    private final Set<String> roles;

    /**
     * public constructor of UserCredentialsImpl
     * @param userId - id of user
     * @param username - email of user
     * @param roles - roles of user
     */
    @JsonCreator
    public UserCredentialsImpl(final String userId, final String username, final Collection<String> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
    }

    /**
     * Get id of user
     * @return id of user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Get email of user
     * @return email of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get roles of user
     * @return roles of user
     */
    public Set<String> getRoles() {
        return roles;
    }

}


