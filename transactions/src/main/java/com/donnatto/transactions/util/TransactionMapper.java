package com.donnatto.transactions.util;

import com.donnatto.transactions.dto.TransactionResponseDTO;
import com.donnatto.transactions.entity.Account;
import com.donnatto.transactions.entity.Transaction;

public class TransactionMapper {
    
    private TransactionMapper() {}
    
    
    public static TransactionResponseDTO mapEntityToDto(Transaction transaction) {
        Account transactionAccount = transaction.getAccount();
        return TransactionResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .timestamp(transaction.getTimestamp())
                .operationType(transaction.getType())
                .amount(transaction.getAmount())
                .accountNumber(transactionAccount.getAccountId())
                .accountType(transactionAccount.getAccountType())
                .initialBalance(transaction.getInitialBalance())
                .currentBalance(transaction.getCurrentBalance())
                .transactionStatus(transaction.getTransactionStatus())
                .build();
    }
}
