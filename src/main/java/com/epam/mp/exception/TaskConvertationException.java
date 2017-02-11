package com.epam.mp.exception;

public class TaskConvertationException extends RuntimeException {

    public TaskConvertationException() {
        super();
    }

    public TaskConvertationException(String message) {
        super(message);
    }

    public TaskConvertationException(String message, Throwable cause) {
        super(message, cause);
    }
}
