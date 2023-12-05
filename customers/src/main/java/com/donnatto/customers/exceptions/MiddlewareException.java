package com.donnatto.customers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MiddlewareException extends RuntimeException {
    private final HttpStatus status;
    private final String userMessage;
}
