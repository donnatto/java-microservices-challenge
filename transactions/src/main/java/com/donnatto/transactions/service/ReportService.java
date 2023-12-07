package com.donnatto.transactions.service;

import com.donnatto.transactions.dto.AccountReportDTO;
import com.donnatto.transactions.dto.TransactionReportDTO;
import com.donnatto.transactions.entity.Account;
import com.donnatto.transactions.exceptions.CustomerAccountsNotFoundException;
import com.donnatto.transactions.repository.AccountRepository;
import com.donnatto.transactions.repository.TransactionRepository;
import com.donnatto.transactions.util.ReportMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportService {
    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
    public List<AccountReportDTO> getCustomerAccountStatus(UUID customerId, LocalDateTime from, LocalDateTime to) {
        List<Account> customerAccounts = accountRepository.findAllActiveAccountsByCustomerId(customerId);
        if (customerAccounts.isEmpty()) {
            throw new CustomerAccountsNotFoundException();
        }
        return customerAccounts
                .stream()
                .map(account -> {
                    List<TransactionReportDTO> transactions = transactionRepository
                            .findAllByAccountIdAndDateRange(account.getAccountId(), from, to)
                            .stream()
                            .map(ReportMapper::mapTransactionEntityToReportDto)
                            .toList();
                    return AccountReportDTO.builder()
                            .accountId(account.getAccountId())
                            .accountType(account.getAccountType())
                            .customerName(account.getCustomerName())
                            .currentBalance(account.getCurrentBalance())
                            .transactions(transactions)
                            .build();
                })
                .toList();
        
    }
}
