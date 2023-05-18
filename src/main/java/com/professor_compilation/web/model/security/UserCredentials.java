package com.professor_compilation.web.model.security;

import java.util.Set;

/**
 * class UserCredentials
 */
public interface UserCredentials {

    /**
     * Get id of user
     * @return id of user
     */
    String getUserId();

    /**
     * Get email of user
     * @return email of user
     */
    String getUsername();

    /**
     * Get roles of user
     * @return roles of user
     */
    Set<String> getRoles();

}
