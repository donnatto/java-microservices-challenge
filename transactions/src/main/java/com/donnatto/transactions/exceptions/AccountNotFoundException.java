package com.donnatto.transactions.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AccountNotFoundException extends MiddlewareException {
    public AccountNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Account doesn't exist");
    }
}
