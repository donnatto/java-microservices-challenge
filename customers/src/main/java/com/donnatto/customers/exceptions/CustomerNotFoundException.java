package com.donnatto.customers.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerNotFoundException extends MiddlewareException {
    public CustomerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User doesn't exist");
    }
}
