package com.donnatto.customers.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateAccountEvent {
    private final Long accountId;
    private final String customerName;
    private final CustomerStatus status;
}
