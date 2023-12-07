package com.donnatto.transactions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionReportDTO {
    
    private final UUID transactionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime timestamp;
    private final OperationType operationType;
    private final Long amount;
    private final Long initialBalance;
    private final Long finalBalance;
    private final TransactionStatus transactionStatus;
}
