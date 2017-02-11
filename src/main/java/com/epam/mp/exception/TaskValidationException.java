package com.epam.mp.exception;

public class TaskValidationException extends RuntimeException {

    public TaskValidationException() {
        super();
    }

    public TaskValidationException(String message) {
        super(message);
    }

    public TaskValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
