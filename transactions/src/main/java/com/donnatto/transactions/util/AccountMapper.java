package com.donnatto.transactions.util;

import com.donnatto.transactions.dto.AccountRequestDTO;
import com.donnatto.transactions.dto.AccountResponseDTO;
import com.donnatto.transactions.dto.PatchAccountRequestDTO;
import com.donnatto.transactions.entity.Account;

public class AccountMapper {
    
    private AccountMapper() {}
    
    public static void mapDtoToEntity(AccountRequestDTO requestDTO, Account account) {
        account.setAccountType(requestDTO.getAccountType());
        account.setClientId(requestDTO.getClientId());
        account.setInitialBalance(requestDTO.getInitialBalance());
        account.setCurrentBalance(requestDTO.getInitialBalance());
    }
    
    public static void mapPatchDtoToEntity(PatchAccountRequestDTO requestDTO, Account account) {
        requestDTO.getAccountType().ifPresent(account::setAccountType);
        requestDTO.getClientId().ifPresent(account::setClientId);
    }
    
    public static AccountResponseDTO mapEntityToDTO(Account account) {
        return AccountResponseDTO.builder()
                .accountId(account.getAccountId())
                .accountType(account.getAccountType())
                .clientId(account.getClientId())
                .clientName(account.getClientName())
                .initialBalance(account.getInitialBalance())
                .currentBalance(account.getCurrentBalance())
                .creationStatus(account.getCreationStatus())
                .accountStatus(account.getAccountStatus())
                .build();
    }
}
