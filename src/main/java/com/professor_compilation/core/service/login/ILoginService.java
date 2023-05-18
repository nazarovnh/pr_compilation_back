package com.professor_compilation.core.service.login;

import com.professor_compilation.core.entity.user.User;
import com.professor_compilation.web.model.user.SignInRequest;


/**
 * interface ILoginService
 */
public interface ILoginService {
    /**
     * Login user in system
     * @param login - login request
     * @return info about user
     */
    User login(final SignInRequest login);
}

