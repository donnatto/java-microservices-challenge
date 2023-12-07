package com.donnatto.transactions.util;

import com.donnatto.transactions.dto.TransactionReportDTO;
import com.donnatto.transactions.entity.Transaction;

public class ReportMapper {
    
    private ReportMapper() {}
    
    public static TransactionReportDTO mapTransactionEntityToReportDto(Transaction transaction) {
        return TransactionReportDTO.builder()
                .transactionId(transaction.getTransactionId())
                .timestamp(transaction.getTimestamp())
                .operationType(transaction.getType())
                .amount(transaction.getAmount())
                .initialBalance(transaction.getInitialBalance())
                .finalBalance(transaction.getFinalBalance())
                .transactionStatus(transaction.getTransactionStatus())
                .build();
    }
}
