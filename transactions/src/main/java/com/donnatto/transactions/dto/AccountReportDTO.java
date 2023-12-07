package com.donnatto.transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountReportDTO {
    
    private final Long accountId;
    private final AccountType accountType;
    private final String customerName;
    private final Long currentBalance;
    private final List<TransactionReportDTO> transactions;
}
