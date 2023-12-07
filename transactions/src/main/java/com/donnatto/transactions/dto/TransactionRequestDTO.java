package com.donnatto.transactions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDTO {
    @NotBlank(message = "The operation type is required")
    private String type;
    @NotNull(message = "The operation amount is required")
    private Long amount;
    @NotNull(message = "The account ID is required")
    private Long accountId;
}
