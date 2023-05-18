package com.professor_compilation.core.model.exception.user;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
    private HttpStatus status = null;

    public UserException(final String s, final HttpStatus status) {
        super(s);
        this.status = status;
    }

    /**
     * class SignUpFailedException
     *
     * @param status - http status
     */
    public UserException(final HttpStatus status) {
        this.status = status;
    }

    /**
     * public constructor of SignUpFailedException
     *
     * @param s         - message of exception
     * @param throwable - throwable exception
     * @param status    - status of http response
     */
    public UserException(final String s, final Throwable throwable, final HttpStatus status) {
        super(s, throwable);
        this.status = status;
    }


    /**
     * public constructor of SignUpFailedException
     *
     * @param throwable - throwable exception
     * @param status    - status of http response
     */
    public UserException(final Throwable throwable, final HttpStatus status) {
        super(throwable);
        this.status = status;
    }

    /**
     * public constructor of SignUpFailedException
     *
     * @param s         - message of exception
     * @param throwable - throwable exception
     * @param b         - enableSuppression
     * @param b1        - writableStackTrace
     * @param status    - status of http response
     */
    public UserException(final String s, final Throwable throwable, final boolean b, final boolean b1, final HttpStatus status) {
        super(s, throwable, b, b1);
        this.status = status;
    }

    /**
     * Get http status
     *
     * @return http status
     */
    public HttpStatus getStatus() {
        return status;
    }
}
