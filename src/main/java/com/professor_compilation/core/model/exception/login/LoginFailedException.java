package com.professor_compilation.core.model.exception.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * class LoginFailedException
 * Using for exception related with login
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {
    private Integer status = null;

    /**
     * public constructor of LoginFailedException
     * @param message - massage of exception
     */
    public LoginFailedException(final String message) {
        super(message);
    }

    /**
     * public constructor of LoginFailedException
     *
     * @param s      - message
     * @param status - http status
     */
    public LoginFailedException(final String s, final Integer status) {
        super(s);
        this.status = status;
    }


    public LoginFailedException(final String s, final HttpStatus status) {
        super(s);
        this.status = status.value();
    }

    /**
     * public constructor of LoginFailedException
     *
     * @param throwable - throwable object
     * @param status    - http status
     */
    public LoginFailedException(final Throwable throwable, final Integer status) {
        super(throwable);
        this.status = status;
    }

    /**
     * Get http status
     *
     * @return http status
     */
    public Integer getStatus() {
        return status;
    }
}



