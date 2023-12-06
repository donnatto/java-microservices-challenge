package com.donnatto.transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDTO {
    
    private final Long accountId;
    private final AccountType accountType;
    private final String clientId;
    private final String clientName;
    private final Long initialBalance;
    private final Long currentBalance;
    private final AccountCreationStatus creationStatus;
    private final AccountStatus accountStatus;
}
