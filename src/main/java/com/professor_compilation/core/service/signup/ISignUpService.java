package com.professor_compilation.core.service.signup;


import com.professor_compilation.core.model.exception.signup.SignUpException;
import com.professor_compilation.web.model.user.SignUpRequest;

/**
 * interface ISignUpService
 */
public interface ISignUpService {

    /**
     * Sign up user in system
     * @param request - sign up request
     * @throws SignUpException - SignUpFailedException
     */
    void signUp(final SignUpRequest request) throws SignUpException;
}

