package com.professor_compilation.core.service.token.jwt;


import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.web.model.security.UserCredentials;

/**
 * interface JwtTokenService
 */
public interface JwtTokenService {

    /**
     * Decode of jwt token
     * @param token the token string to parse
     * @return UserCredentials
     */
    UserCredentials parseToken(String token);

    /**
     * Create access token. Set basic claims in token
     * @param user contains User to be represented as token
     * @return jwt access token
     */
    String createAccessToken(User user);

    /**
     * Create refresh token. Set basic claims in token
     * @param user contains User to be represented as token
     * @return jwt refresh token
     */
    String createRefreshToken(User user);


}


