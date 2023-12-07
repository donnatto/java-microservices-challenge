package com.donnatto.transactions.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerAccountsNotFoundException extends MiddlewareException{
    public CustomerAccountsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "This customer doesn't have any accounts");
    }
}
