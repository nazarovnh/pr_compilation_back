package com.professor_compilation.core.model.exception.environment;

public class EnvironmentException extends Exception {
    public EnvironmentException() {
    }

    public EnvironmentException(final String message) {
        super(message);
    }

    public EnvironmentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EnvironmentException(final Throwable cause) {
        super(cause);
    }

    public EnvironmentException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
