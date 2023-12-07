package com.donnatto.transactions.entity;

import com.donnatto.transactions.dto.AccountCreationStatus;
import com.donnatto.transactions.dto.AccountStatus;
import com.donnatto.transactions.dto.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Data
@Entity(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Account {
    
    @Id
    private final Long accountId = (long) (Math.random() * 9000000000L) + 1000000000L;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private UUID customerId;
    private String customerName;
    private Long initialBalance;
    private Long currentBalance;
    @Enumerated(EnumType.STRING)
    private AccountCreationStatus creationStatus;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
