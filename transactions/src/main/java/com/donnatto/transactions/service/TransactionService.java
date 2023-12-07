package com.donnatto.transactions.service;

import com.donnatto.transactions.dto.OperationType;
import com.donnatto.transactions.dto.TransactionRequestDTO;
import com.donnatto.transactions.dto.TransactionResponseDTO;
import com.donnatto.transactions.dto.TransactionStatus;
import com.donnatto.transactions.entity.Account;
import com.donnatto.transactions.entity.Transaction;
import com.donnatto.transactions.exceptions.AccountNotFoundException;
import com.donnatto.transactions.exceptions.InsufficientBalanceException;
import com.donnatto.transactions.exceptions.TransactionNotFoundException;
import com.donnatto.transactions.repository.AccountRepository;
import com.donnatto.transactions.repository.TransactionRepository;
import com.donnatto.transactions.util.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    
    public List<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::mapEntityToDto)
                .toList();
    }
    
    public List<TransactionResponseDTO> getAccountTransactions(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new AccountNotFoundException();
        }
        return transactionRepository.findAllByAccountId(accountId).stream()
                .map(TransactionMapper::mapEntityToDto)
                .toList();
    }
    
    public TransactionResponseDTO getTransaction(UUID transactionId) {
        return transactionRepository.findById(transactionId)
                .map(TransactionMapper::mapEntityToDto)
                .orElseThrow(TransactionNotFoundException::new);
    }
    
    @Transactional
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO) {
        Account account = accountRepository
                .findById(requestDTO.getAccountId())
                .orElseThrow(AccountNotFoundException::new);
        Long initialBalance = account.getCurrentBalance();
        Long transactionAmount = requestDTO.getAmount();
        OperationType type = OperationType.valueOf(requestDTO.getType());
        if (type == OperationType.DEPOSIT) {
            account.setCurrentBalance(initialBalance + transactionAmount);
        } else if (type == OperationType.WITHDRAWAL) {
            if (transactionAmount > initialBalance) {
                throw new InsufficientBalanceException();
            }
            account.setCurrentBalance(initialBalance - transactionAmount);
        }
        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(type);
        transaction.setAmount(transactionAmount);
        transaction.setAccount(account);
        transaction.setInitialBalance(initialBalance);
        transaction.setCurrentBalance(account.getCurrentBalance());
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        Transaction savedTransaction = transactionRepository.save(transaction);
        accountRepository.save(account);
        return TransactionMapper.mapEntityToDto(savedTransaction);
    }
    
    @Transactional
    public void revertTransaction(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(TransactionNotFoundException::new);
        Account account = transaction.getAccount();
        Long currentBalance = account.getCurrentBalance();
        Long transactionAmount = transaction.getAmount();
        OperationType type = transaction.getType();
        account.setCurrentBalance(type == OperationType.DEPOSIT ?
                currentBalance - transactionAmount :
                currentBalance + transactionAmount);
        accountRepository.save(account);
        transaction.setTransactionStatus(TransactionStatus.REVERTED);
        transactionRepository.save(transaction);
    }
}
