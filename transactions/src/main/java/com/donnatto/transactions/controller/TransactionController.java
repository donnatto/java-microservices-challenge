package com.donnatto.transactions.controller;

import com.donnatto.transactions.dto.TransactionRequestDTO;
import com.donnatto.transactions.dto.TransactionResponseDTO;
import com.donnatto.transactions.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    
    private final TransactionService service;
    
    @GetMapping
    public List<TransactionResponseDTO> getAllTransactions(@RequestParam Optional<Long> accountId) {
        if (accountId.isEmpty()) {
            return service.getTransactions();
        }
        return service.getAccountTransactions(accountId.get());
    }
    
    @GetMapping("/{transactionId}")
    public TransactionResponseDTO getTransaction(@PathVariable String transactionId) {
        return service.getTransaction(UUID.fromString(transactionId));
    }
    
    @PostMapping
    public TransactionResponseDTO createTransaction(@Valid @RequestBody TransactionRequestDTO requestDTO) {
        return service.saveTransaction(requestDTO);
    }
    
    @DeleteMapping("/{transactionId}")
    public void revertTransaction(@PathVariable String transactionId) {
        service.revertTransaction(UUID.fromString(transactionId));
    }
}
