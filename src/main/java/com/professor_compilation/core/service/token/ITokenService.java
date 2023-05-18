package com.professor_compilation.core.service.token;


import com.professor_compilation.core.model.token.Token;
import com.professor_compilation.web.model.token.RefreshRequest;

/**
 * interface ITokenService
 */
public interface ITokenService {
    /**
     * Update refresh token of user in database
     * @param userId - id of user
     * @param refreshToken - refresh token
     */
    void updateRefreshToken(String userId, String refreshToken);

    /**
     * Give new access and refresh token to user and update refresh token of user in database
     * @param request - refresh request
     * @return new pair of tokens
     */
    Token updateTokens(final RefreshRequest request);

    /**
     * Validate of equality of refresh token
     * @param userId - id of user
     * @param refreshToken - refresh token
     */
    void validateRefreshToken(final String userId, final String refreshToken);
}

