package com.joaowudarski.host.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joaowudarski.exception.InvalidAuthenticationException;
import com.joaowudarski.exception.InvalidTypeException;
import com.joaowudarski.exception.ObjectUpdateException;
import com.joaowudarski.host.data.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAuthenticationException(
            InvalidAuthenticationException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Authentication Error",
                ex.getMessage());
    }

    @ExceptionHandler(ObjectUpdateException.class)
    public ResponseEntity<ErrorResponse> handleObjectUpdateException(
            ObjectUpdateException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Object Update Error",
                ex.getMessage());
    }

    @ExceptionHandler(InvalidTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTypeException(
            InvalidTypeException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid Type Error",
                ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Entity Not Found",
                ex.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResponse> handleJsonProcessingException(
            JsonProcessingException ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "JSON Processing Error",
                "Error processing JSON: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status, String error, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .error(error)
                .message(message)
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
} 