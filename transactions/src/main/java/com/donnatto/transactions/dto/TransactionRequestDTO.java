package com.donnatto.transactions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDTO {
    @NotNull(message = "The operation type is required")
    private OperationType type;
    @NotNull(message = "The operation amount is required")
    private Long amount;
    @NotNull(message = "The account ID is required")
    private Long accountId;
}
