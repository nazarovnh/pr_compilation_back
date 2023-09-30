package com.professor_compilation.core.model.token;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Model to send token.
 */
@Data
@RequiredArgsConstructor
public class RefreshToken {
    private final String tokenId;
    private final String refreshToken;
}
