package com.professor_compilation.core.service.token;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Settings to the JWT token.
 */
@Component
public class JwtSettings {

    private final String tokenIssuer;
    private final String tokenSigningKey;
    private final int aAccessTokenDuration;
    private final int aRefreshTokenDuration;

    /**
     * public constructor of JwtSettings
     * @param tokenIssuer - issuer of token
     * @param tokenSigningKey - signing key of token
     * @param aAccessTokenDuration - duration of ability access token
     * @param aRefreshTokenDuration - duration of ability refresh token
     */
    public JwtSettings(@Value("${jwt.issuer}") final String tokenIssuer,
                       @Value("${jwt.signingKey}") final String tokenSigningKey,
                       @Value("${jwt.aAccessTokenDuration}") final int aAccessTokenDuration,
                       @Value("${jwt.aRefreshTokenDuration}") final int aRefreshTokenDuration) {
        this.tokenIssuer = tokenIssuer;
        this.tokenSigningKey = tokenSigningKey;
        this.aAccessTokenDuration = aAccessTokenDuration;
        this.aRefreshTokenDuration = aRefreshTokenDuration;
    }

    /**
     * Get issuer of token
     * @return issuer of token
     */
    public String getTokenIssuer() {
        return tokenIssuer;
    }

    /**
     * Get signing key of token
     * @return signing key of token
     */
    public byte[] getTokenSigningKey() {
        return tokenSigningKey.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Get duration of ability access token
     * @return duration of ability  access token
     */
    public Duration getAccessTokenExpiredIn() {
        return Duration.ofMinutes(aAccessTokenDuration);
    }


    /**
     * Get duration of ability refresh token
     * @return duration of ability refresh token
     */
    public Duration getRefreshTokenDuration() {
        return Duration.ofMinutes(aRefreshTokenDuration);
    }
}
