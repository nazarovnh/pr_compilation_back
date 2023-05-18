package com.professor_compilation.core.model.exception.subject;

import org.springframework.http.HttpStatus;

public class SubjectException extends Exception {
    private HttpStatus status = null;

    public SubjectException(HttpStatus status) {
        this.status = status;
    }

    public SubjectException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public SubjectException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public SubjectException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public SubjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
