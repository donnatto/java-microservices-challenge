package com.donnatto.transactions.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TransactionNotFoundException extends MiddlewareException {
    public TransactionNotFoundException() {
        super(HttpStatus.NOT_FOUND, "The transaction doesn't exist");
    }
}
