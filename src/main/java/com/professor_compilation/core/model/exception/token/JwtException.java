package com.professor_compilation.core.model.exception.token;

import org.springframework.http.HttpStatus;

public class JwtException extends RuntimeException {
    private HttpStatus status = null;

    /**
     * class SignUpFailedException
     * @param status - http status
     */
    public JwtException(final HttpStatus status) {
        this.status = status;
    }


    /**
     * public constructor of SignUpFailedException
     * @param s - message of exception
     * @param status - status of http response
     */
    public JwtException(final String s, final HttpStatus status) {
        super(s);
        this.status = status;
    }
    /**
     * public constructor of SignUpFailedException
     * @param s - message of exception
     * @param throwable - throwable exception
     * @param status - status of http response
     */
    public JwtException(final String s, final Throwable throwable, final HttpStatus status) {
        super(s, throwable);
        this.status = status;
    }


    /**
     * public constructor of SignUpFailedException
     * @param throwable - throwable exception
     * @param status - status of http response
     */
    public JwtException(final Throwable throwable, final HttpStatus status) {
        super(throwable);
        this.status = status;
    }

    /**
     * public constructor of SignUpFailedException
     * @param s - message of exception
     * @param throwable - throwable exception
     * @param b - enableSuppression
     * @param b1 - writableStackTrace
     * @param status - status of http response
     */
    public JwtException(final String s, final Throwable throwable, final boolean b, final boolean b1, final HttpStatus status) {
        super(s, throwable, b, b1);
        this.status = status;
    }

    /**
     * Get http status
     * @return http status
     */
    public HttpStatus getStatus() {
        return status;
    }
}

