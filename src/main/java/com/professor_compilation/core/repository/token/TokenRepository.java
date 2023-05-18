package com.professor_compilation.core.repository.token;

import com.professor_compilation.core.model.token.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class TokenRepository implements ITokenRepository<Token, String> {
    private final JdbcOperations jdbcOperations;

    public TokenRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public Optional<Void> updateRefreshToken(final String userId, final String refreshToken) {
        log.info("ADD NEW REFRESH TOKEN FOR USER" + userId);
        try {
            int rowsUpdated = jdbcOperations.update(
                    "UPDATE token SET refresh_token = ?  WHERE user_id = ?",
                    refreshToken, userId
            );
            if (rowsUpdated == 0) {
                log.warn("No rows were updated for user {}", userId);
                return Optional.empty();
            }
        } catch (DataAccessException e) {
            log.error("Error updating refresh token for user {}", userId, e);
            throw e;
        }
        return Optional.ofNullable(null);
    }

    @Override
    public String initToken(final String userId) {
        log.info("INIT REFRESH TOKEN FOR USER {}", userId);
        final String tokenId = UUID.randomUUID().toString();
        jdbcOperations.update("INSERT INTO token (token_id ,user_id, refresh_token) " +
                "VALUES(?, ?, ?)", tokenId, userId, null);
        return tokenId;
    }

    @Override
    public Optional<String> getCurrentRefreshToken(final String userId) {
        log.info("get refresh token's user" + userId);
        try {
            String refreshToken = jdbcOperations.queryForObject(
                    "SELECT refresh_token FROM token WHERE user_id = ?",
                    new Object[]{userId},
                    String.class
            );
            return Optional.ofNullable(refreshToken);
        } catch (EmptyResultDataAccessException e) {
            log.warn("No refresh token found for user {}", userId);
            return Optional.empty();
        } catch (DataAccessException e) {
            log.error("Error getting refresh token for user {}", userId, e);
            throw e;
        }
    }

}
