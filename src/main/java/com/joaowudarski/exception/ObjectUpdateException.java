package com.joaowudarski.exception;

public class ObjectUpdateException extends RuntimeException {
    
    public ObjectUpdateException(String message) {
        super(message);
    }

    public ObjectUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
} 