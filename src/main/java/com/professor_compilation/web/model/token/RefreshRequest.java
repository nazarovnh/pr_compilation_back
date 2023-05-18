package com.professor_compilation.web.model.token;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class RefreshRequest
 */
public class RefreshRequest {
    private String refreshToken;

    /**
     * public constructor of RefreshRequest
     * @param refreshToken - refresh token
     */
    @JsonCreator
    public RefreshRequest(@JsonProperty("refreshToken") final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Get refresh token
     * @return refresh token
     */
    public String getRefreshToken() {
        return refreshToken;
    }
}

