package com.donnatto.transactions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRequestDTO {
    
    @NotBlank(message = "The account type is required")
    private final String accountType;
    @NotBlank(message = "The customer ID is required")
    private final String customerId;
    @NotNull(message = "The initial balance is required")
    private final Long initialBalance;
}
