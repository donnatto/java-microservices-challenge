package com.donnatto.transactions.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountEvent {
    private final Long accountId;
    private final String customerId;
}
