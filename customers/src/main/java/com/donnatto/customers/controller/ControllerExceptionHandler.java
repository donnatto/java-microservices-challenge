package com.donnatto.customers.controller;

import com.donnatto.customers.exceptions.ApiError;
import com.donnatto.customers.exceptions.MiddlewareException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    private static final String DEFAULT_ERROR_MESSAGE =
            "We're sorry. We couldn't complete the request but are working to fix the issue.";
    private static final String VALIDATION_ERROR_MESSAGE =
            "Some information was entered incorrectly or is missing. Please check your information.";
    
    @ExceptionHandler(MiddlewareException.class)
    public final ResponseEntity<ApiError> handleMiddlewareException(MiddlewareException ex) {
        ApiError error = createApiError(
                ex.getStatus(),
                ex.getUserMessage(),
                ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError error = createApiError(
                HttpStatus.BAD_REQUEST,
                VALIDATION_ERROR_MESSAGE,
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ApiError> handleArgNotValidException(MethodArgumentNotValidException ex) {
        ApiError error = createApiError(
                HttpStatus.BAD_REQUEST,
                VALIDATION_ERROR_MESSAGE,
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleUnknownException(Exception ex) {
        ApiError error = createApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                DEFAULT_ERROR_MESSAGE,
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    private ApiError createApiError(HttpStatus status, String userMessage, String debugMessage) {
        return ApiError.builder()
                .httpStatus(status)
                .userMessage(userMessage)
                .debugMessage(debugMessage)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
