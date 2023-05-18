package com.professor_compilation.core.service.signup;


import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.model.exception.signup.SignUpException;
import com.professor_compilation.core.model.token.RefreshToken;
import com.professor_compilation.core.model.token.Token;
import com.professor_compilation.core.repository.token.ITokenRepository;
import com.professor_compilation.core.repository.user.IUserRepository;
import com.professor_compilation.core.security.PasswordEncoder;
import com.professor_compilation.web.model.user.SignUpRequest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * class SignUpService
 */
/**
 * class SignUpService
 */
public class SignUpService implements ISignUpService {
    private final IUserRepository<User, String> userRepository;
    private final ITokenRepository<RefreshToken, String> tokenRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * class SignUpService
     * @param userRepository - repository of user
     * @param tokenRepository - repository of tokens
     * @param passwordEncoder - encoder of user
     */
    public SignUpService(final IUserRepository userRepository,
                         final ITokenRepository tokenRepository,
                         final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Sign up user in system
     * @param request - sign up request
     * @throws SignUpException - SignUpFailedException
     */
    @Override
    public void signUp(final SignUpRequest request) throws SignUpException {
        try {
            validateExistUser(request.getEmail());
            String userId = userRepository.addUser(request.getEmail(), passwordEncoder.encode(request.getPassword()));
            tokenRepository.initToken(userId);
        } catch (SignUpException rpe) {
            throw new SignUpException(rpe.getMessage(), rpe.getStatus());
        }
    }

    /**
     * Validate that player exists in system.
     * @param email - email of user
     */
    void validateExistUser(final String email) throws SignUpException {
        Optional user = userRepository.getUserInfoByEmail(email);
        if (user.isPresent()) {
            throw new SignUpException(
                    "User " + email + " exist", HttpStatus.BAD_REQUEST
            );
        }
    }
}


