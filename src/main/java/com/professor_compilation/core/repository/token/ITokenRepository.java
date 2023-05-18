package com.professor_compilation.core.repository.token;

import java.util.Optional;

public interface ITokenRepository<T, ID> {

    /**
     * Updated refresh token of user
     * @param userId - id of user
     * @param refreshToken - new refresh token
     */
    Optional<Void> updateRefreshToken(final ID userId, final String refreshToken);

    /**
     * Init refresh token for user
     * @param userId - id of user
     */
    ID initToken(final ID userId);

    /**
     * Get current refresh token of user
     * @param userId - id of user
     * @return refresh token
     */

    Optional<String> getCurrentRefreshToken(String userId);
}

