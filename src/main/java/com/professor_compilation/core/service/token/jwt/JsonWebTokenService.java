package com.professor_compilation.core.service.token.jwt;


import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.service.token.JwtSettings;
import com.professor_compilation.web.model.security.UserCredentials;
import com.professor_compilation.web.model.security.UserCredentialsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Service to generate and parse JWT tokens.
 */
public class JsonWebTokenService implements JwtTokenService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtSettings settings;
    private static final String ROLES = "roles";

    /**
     * public constructor of JsonWebTokenService
     * @param settings        - settings
     */
    public JsonWebTokenService(final JwtSettings settings) {
        this.settings = settings;
    }

    /**
     * Create access token. Set basic claims in token
     *
     * @param user contains User to be represented as token
     * @return jwt access token
     */
    @Override
    public String createAccessToken(final User user) {
        logger.debug("Generating access token for {}", user.getUsername());

        Instant now = Instant.now();

        Claims claims = Jwts.claims()
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now))
                .setSubject(user.getUsername())
                .setId(user.getUserId())
                .setExpiration(Date.from(now.plus(settings.getAccessTokenExpiredIn())));
        claims.put(ROLES, user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
    }

    /**
     * Create refresh token. Set basic claims in token
     *
     * @param user contains User to be represented as token
     * @return jwt refresh token
     */
    @Override
    public String createRefreshToken(final User user) {
        logger.debug("Generating refresh token for {}", user.getUsername());

        Instant now = Instant.now();

        Claims claims = Jwts.claims()
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now))
                .setSubject(user.getUsername())
                .setId(user.getUserId())
                .setExpiration(Date.from(now.plus(settings.getRefreshTokenDuration())));
        claims.put(ROLES, user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
    }

    /**
     * Decode of jwt token
     * @param token the token string to parse
     * @return UserCredentials
     */
    @Override
    @SuppressWarnings("unchecked")
    public UserCredentials parseToken(final String token) {
        logger.debug("Parsing token {}", token);

        Jws<Claims> claims = Jwts.parser().setSigningKey(settings.getTokenSigningKey()).parseClaimsJws(token);

        String subject = claims.getBody().getSubject();
        String id = claims.getBody().getId();
        List<String> roles = claims.getBody().get(ROLES, List.class);

        return new UserCredentialsImpl(id, subject, Collections.unmodifiableList(roles));
    }

}
