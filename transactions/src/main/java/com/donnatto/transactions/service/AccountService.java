package com.donnatto.transactions.service;

import com.donnatto.transactions.dto.AccountCreationStatus;
import com.donnatto.transactions.dto.AccountRequestDTO;
import com.donnatto.transactions.dto.AccountResponseDTO;
import com.donnatto.transactions.dto.AccountStatus;
import com.donnatto.transactions.dto.PatchAccountRequestDTO;
import com.donnatto.transactions.entity.Account;
import com.donnatto.transactions.exceptions.AccountNotFoundException;
import com.donnatto.transactions.repository.AccountRepository;
import com.donnatto.transactions.util.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    
    public List<AccountResponseDTO> getAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::mapEntityToDTO)
                .toList();
    }
    
    public AccountResponseDTO getAccount(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::mapEntityToDTO)
                .orElseThrow(AccountNotFoundException::new);
    }
    
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        Account account = new Account();
        account.setCreationStatus(AccountCreationStatus.ACCOUNT_CREATED);
        account.setAccountStatus(AccountStatus.PENDING);
        AccountMapper.mapDtoToEntity(requestDTO, account);
        Account savedAccount = accountRepository.saveAndFlush(account);
        // TODO: send create account event to customer microservice
        return AccountMapper.mapEntityToDTO(savedAccount);
    }
    
    public AccountResponseDTO patchAccount(Long id, PatchAccountRequestDTO requestDTO) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        AccountMapper.mapPatchDtoToEntity(requestDTO, account);
        Account updatedAccount = accountRepository.saveAndFlush(account);
        // TODO: send update account event to customer microservice
        return AccountMapper.mapEntityToDTO(updatedAccount);
    }
    
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        account.setAccountStatus(AccountStatus.INACTIVE);
        accountRepository.save(account);
    }
}
