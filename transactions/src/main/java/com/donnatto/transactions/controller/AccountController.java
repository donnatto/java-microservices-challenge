package com.donnatto.transactions.controller;

import com.donnatto.transactions.dto.AccountRequestDTO;
import com.donnatto.transactions.dto.AccountResponseDTO;
import com.donnatto.transactions.dto.PatchAccountRequestDTO;
import com.donnatto.transactions.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    
    private final AccountService service;
    
    @GetMapping
    public List<AccountResponseDTO> getAccounts() {
        return service.getAccounts();
    }
    
    @GetMapping("/{accountId}")
    public AccountResponseDTO getAccount(@PathVariable Long accountId) {
        return service.getAccount(accountId);
    }
    
    @PostMapping
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO requestDTO) {
        return service.createAccount(requestDTO);
    }
    
    @PatchMapping("/{accountId}")
    public AccountResponseDTO patchAccount(
            @PathVariable Long accountId, @RequestBody PatchAccountRequestDTO requestDTO) {
        return service.patchAccount(accountId, requestDTO);
    }
    
    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        service.deleteAccount(accountId);
    }
}
