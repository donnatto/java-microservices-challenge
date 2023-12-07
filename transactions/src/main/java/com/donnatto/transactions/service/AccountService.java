package com.donnatto.transactions.service;

import com.donnatto.transactions.dto.AccountCreationStatus;
import com.donnatto.transactions.dto.AccountRequestDTO;
import com.donnatto.transactions.dto.AccountResponseDTO;
import com.donnatto.transactions.dto.AccountStatus;
import com.donnatto.transactions.dto.PatchAccountRequestDTO;
import com.donnatto.transactions.entity.Account;
import com.donnatto.transactions.exceptions.AccountNotFoundException;
import com.donnatto.transactions.kafka.MessageProducer;
import com.donnatto.transactions.repository.AccountRepository;
import com.donnatto.transactions.util.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final MessageProducer messageProducer;
    private static final String ACCOUNT_EVENT_TOPIC = "account-event-topic";
    
    public List<AccountResponseDTO> getAccounts() {
        return accountRepository.findAllActiveAccounts().stream()
                .map(AccountMapper::mapEntityToDTO)
                .toList();
    }
    
    public AccountResponseDTO getAccount(Long id) {
        return accountRepository.findActiveAccountById(id)
                .map(AccountMapper::mapEntityToDTO)
                .orElseThrow(AccountNotFoundException::new);
    }
    
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        Account account = new Account();
        account.setCreationStatus(AccountCreationStatus.ACCOUNT_CREATED);
        account.setAccountStatus(AccountStatus.PENDING);
        AccountMapper.mapDtoToEntity(requestDTO, account);
        Account savedAccount = accountRepository.saveAndFlush(account);
        messageProducer.sendAccountEvent(ACCOUNT_EVENT_TOPIC, savedAccount);
        return AccountMapper.mapEntityToDTO(savedAccount);
    }
    
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO requestDTO) {
        Account account = accountRepository.findActiveAccountById(id).orElseThrow(AccountNotFoundException::new);
        account.setCreationStatus(AccountCreationStatus.ACCOUNT_CREATED);
        account.setAccountStatus(AccountStatus.PENDING);
        AccountMapper.mapDtoToEntity(requestDTO, account);
        Account updatedAccount = accountRepository.saveAndFlush(account);
        messageProducer.sendAccountEvent(ACCOUNT_EVENT_TOPIC, updatedAccount);
        return AccountMapper.mapEntityToDTO(updatedAccount);
    }
    
    public AccountResponseDTO patchAccount(Long id, PatchAccountRequestDTO requestDTO) {
        Account account = accountRepository.findActiveAccountById(id).orElseThrow(AccountNotFoundException::new);
        AccountMapper.mapPatchDtoToEntity(requestDTO, account);
        account.setAccountStatus(AccountStatus.PENDING);
        Account updatedAccount = accountRepository.saveAndFlush(account);
        messageProducer.sendAccountEvent(ACCOUNT_EVENT_TOPIC, updatedAccount);
        return AccountMapper.mapEntityToDTO(updatedAccount);
    }
    
    public void deleteAccount(Long id) {
        Account account = accountRepository.findActiveAccountById(id).orElseThrow(AccountNotFoundException::new);
        account.setAccountStatus(AccountStatus.INACTIVE);
        accountRepository.save(account);
    }
}
