package com.professor_compilation.core.model.token;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to send token.
 */
public class Token {
    private final String accessToken;
    private final String refreshToken;

    /**
     * public constructor of Token
     * @param accessToken - jwt access token
     * @param refreshToken - jwt refresh token
     */
    @JsonCreator
    public Token(@JsonProperty("accessToken") final String accessToken,
                 @JsonProperty("refreshToken") final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    /**
     * Get access token
     *
     * @return token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Get refresh token
     *
     * @return token
     */
    public String getRefreshToken() {
        return refreshToken;
    }
}
