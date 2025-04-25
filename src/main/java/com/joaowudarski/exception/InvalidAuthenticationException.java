package com.joaowudarski.exception;

public class InvalidAuthenticationException extends RuntimeException {

    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
