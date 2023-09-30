package com.professor_compilation.web.controller.signup;

import com.professor_compilation.core.model.exception.signup.SignUpException;
import com.professor_compilation.core.service.signup.ISignUpService;
import com.professor_compilation.web.model.user.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class SignUpController
 */
@RequestMapping("/signup")
@RestController
@Slf4j
public class SignUpController {
    private final ISignUpService signUpService;

    /**
     * public constructor of SignUpController
     * @param signUpService - service of sign up
     */
    public SignUpController(final ISignUpService signUpService) {
        this.signUpService = signUpService;
    }

    /**
     * Sign up user in system
     * @param request - sign up request
     * @return HttpStatus
     */
    @PostMapping
    public ResponseEntity<HttpStatus> signUp(@RequestBody final SignUpRequest request) {
        try {
            if (!(isString(request.getEmail()) && isString(request.getPassword()))) {
                return ResponseEntity.badRequest().build();
            }
            signUpService.signUp(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SignUpException signUpFailedException) {
            log.warn(signUpFailedException.getMessage());
            return ResponseEntity.status(signUpFailedException.getStatus()).build();
        }
    }

    private boolean isString(final Object testUUID) {
        if (testUUID == null) {
            return false;
        }
        return testUUID instanceof String;
    }
}

