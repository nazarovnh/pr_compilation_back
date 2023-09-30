package com.professor_compilation.core.service.token;

import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.model.exception.token.JwtException;
import com.professor_compilation.core.model.exception.user.UserException;
import com.professor_compilation.core.model.token.Token;
import com.professor_compilation.core.repository.token.ITokenRepository;
import com.professor_compilation.core.repository.user.IUserRepository;
import com.professor_compilation.core.service.token.jwt.JwtTokenService;
import com.professor_compilation.core.service.validator.IValidatorService;
import com.professor_compilation.web.model.security.UserCredentials;
import com.professor_compilation.web.model.token.RefreshRequest;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;

/**
 * class TokenService
 */
public class TokenService implements ITokenService {
    private JwtTokenService jwtTokenService;
    private ITokenRepository<Token, String> tokenRepository;
    private final IUserRepository<User, String> userRepository;
    private final IValidatorService validatorService;

    /**
     * @param jwtTokenService - service of jwt token
     * @param tokenRepository - repository of token
     * @param userRepository  - repository of user
     */
    public TokenService(final JwtTokenService jwtTokenService,
                        final ITokenRepository tokenRepository,
                        final IUserRepository userRepository,
                        final IValidatorService validatorService) {
        this.jwtTokenService = jwtTokenService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.validatorService = validatorService;
    }

    @Override
    public void updateRefreshToken(final String userId, final String refreshToken) {
        tokenRepository.updateRefreshToken(userId, refreshToken);
    }

    @Override
    public Token updateTokens(final RefreshRequest request) {
        try {
            UserCredentials userCredentials = jwtTokenService.parseToken(request.getRefreshToken());
            validateRefreshToken(userCredentials.getUserId(), request.getRefreshToken());
            String userId = userCredentials.getUserId();

            Optional<User> userOptional = userRepository.getUserInfoById(userCredentials.getUserId());
            User user = validatorService.validateExistenceEntity(userOptional, String.format("USER WITH ID %s was not found", userId), UserException.class);
            String accessToken = jwtTokenService.createAccessToken(user);
            String refreshToken = jwtTokenService.createRefreshToken(user);
            tokenRepository.updateRefreshToken(user.getUserId(), refreshToken);
            return new Token(accessToken, refreshToken);
        } catch (ExpiredJwtException e) {
            throw new JwtException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validateRefreshToken(final String userId, final String refreshToken) {
        if (!Objects.equals(tokenRepository.getCurrentRefreshToken(userId).get(), refreshToken)) {
            throw new JwtException("Sorry, token are expired. Login again", HttpStatus.BAD_REQUEST);
        }
    }


}


