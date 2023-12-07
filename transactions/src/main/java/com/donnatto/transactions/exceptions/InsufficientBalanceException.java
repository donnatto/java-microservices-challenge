package com.donnatto.transactions.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InsufficientBalanceException extends MiddlewareException{
    public InsufficientBalanceException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY,
                "The account has insufficient funds. The transaction couldn't be completed" );
    }
}
