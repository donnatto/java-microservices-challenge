package com.donnatto.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountEvent {
    private Long accountId;
    private String customerName;
    private CustomerStatus status;
}
