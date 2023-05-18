package com.professor_compilation.web.controller.login;


import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.core.model.exception.login.LoginFailedException;
import com.professor_compilation.core.model.token.Token;
import com.professor_compilation.core.service.login.ILoginService;
import com.professor_compilation.core.service.token.ITokenService;
import com.professor_compilation.core.service.token.jwt.JwtTokenService;
import com.professor_compilation.web.model.user.SignInRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * class LoginController
 */
@RequestMapping("/signin")
@RestController
public class LoginController {
    private final ILoginService loginService;
    private final JwtTokenService jwtTokenService;
    private final ITokenService tokenService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * public constructor of LoginController
     *
     * @param loginService    - service of login
     * @param jwtTokenService - service of token
     * @param tokenService - service of token
     */
    public LoginController(final ILoginService loginService,
                           final JwtTokenService jwtTokenService,
                           final ITokenService tokenService) {
        this.loginService = loginService;
        this.jwtTokenService = jwtTokenService;
        this.tokenService = tokenService;
    }

    /**
     * Login user info
     *
     * @param login - login request
     * @return user token
     */
    @PostMapping
    public ResponseEntity<Token> create(@RequestBody final SignInRequest login) {
        try {
            if (!(isString(login.getEmail()) && isString(login.getPassword()))) {
                return ResponseEntity.badRequest().build();
            }
            User user = loginService.login(login);
            String accessToken = jwtTokenService.createAccessToken(user);
            String refreshToken = jwtTokenService.createRefreshToken(user);
            tokenService.updateRefreshToken(user.getUserId(), refreshToken);
            return new ResponseEntity<>(new Token(accessToken, refreshToken), HttpStatus.OK);
        } catch (LoginFailedException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(exception.getStatus()).build();
        }
    }


    /**
     * Check is parameter instance of String
     *
     * @param parameter - parameter
     * @return true if parameter instance of String else false
     */
    private boolean isString(final Object parameter) {
        if (parameter == null) {
            return false;
        }
        return parameter instanceof String;
    }
}

