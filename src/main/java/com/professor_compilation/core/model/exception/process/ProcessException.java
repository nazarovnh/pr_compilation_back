package com.professor_compilation.core.model.exception.process;

public class ProcessException extends Exception {
    public ProcessException() {
    }

    public ProcessException(final String message) {
        super(message);
    }

    public ProcessException(final String message,final Throwable cause) {
        super(message, cause);
    }

    public ProcessException(final Throwable cause) {
        super(cause);
    }

    public ProcessException(final String message, final Throwable cause, final boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
