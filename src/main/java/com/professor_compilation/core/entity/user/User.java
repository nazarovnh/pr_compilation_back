package com.professor_compilation.core.entity.user;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * class User contains info about user:
 * - id of user
 * - username of user
 * - roles of user
 * - password of user
 */
public class User {


    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("username")
    private final String username;

    @JsonProperty("roles")
    private final List<String> roles;

    @JsonIgnore
    private final String password;

    /**
     * public constructor of User
     * @param userId - id of user
     * @param username - username of user
     * @param password - password of user
     * @param roles - roles of user
     */
    public User(final String userId, final String username, final String password, final List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * public constructor of User
     * @param userId - id of user
     * @param username - username of user
     * @param roles - roles of user
     */
    public User(final String userId, final List<String> roles, final String username) {
        this.userId = userId;
        this.username = username;
        this.password = null;
        this.roles = roles;
    }


    /**
     * public JsonCreator User
     * @param userId - id of user
     * @param username - username of user
     * @param roles - roles of user
     */
    @JsonCreator
    public User(final String userId, final String username, final List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.password = null;
        this.roles = roles;
    }

    /**
     * Get username of user
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get password of user
     * @return password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get id of user
     * @return id of user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Get roles of user
     * @return roles of user
     */
    public List<String> getRoles() {
        return roles;
    }

}

