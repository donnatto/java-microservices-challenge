package com.donnatto.transactions.util;

import com.donnatto.transactions.dto.AccountRequestDTO;
import com.donnatto.transactions.dto.AccountResponseDTO;
import com.donnatto.transactions.dto.AccountType;
import com.donnatto.transactions.dto.PatchAccountRequestDTO;
import com.donnatto.transactions.entity.Account;

import java.util.UUID;

public class AccountMapper {
    
    private AccountMapper() {}
    
    public static void mapDtoToEntity(AccountRequestDTO requestDTO, Account account) {
        account.setAccountType(AccountType.valueOf(requestDTO.getAccountType()));
        account.setCustomerId(UUID.fromString(requestDTO.getCustomerId()));
        account.setInitialBalance(requestDTO.getInitialBalance());
        account.setCurrentBalance(requestDTO.getInitialBalance());
    }
    
    public static void mapPatchDtoToEntity(PatchAccountRequestDTO requestDTO, Account account) {
        requestDTO.getAccountType().ifPresent(type -> account.setAccountType(AccountType.valueOf(type)));
        requestDTO.getCustomerId().ifPresent(customerId -> account.setCustomerId(UUID.fromString(customerId)));
    }
    
    public static AccountResponseDTO mapEntityToDTO(Account account) {
        return AccountResponseDTO.builder()
                .accountId(account.getAccountId())
                .accountType(account.getAccountType())
                .customerId(account.getCustomerId().toString())
                .customerName(account.getCustomerName())
                .initialBalance(account.getInitialBalance())
                .currentBalance(account.getCurrentBalance())
                .creationStatus(account.getCreationStatus())
                .accountStatus(account.getAccountStatus())
                .build();
    }
}
