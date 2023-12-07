package com.donnatto.transactions.entity;

import com.donnatto.transactions.dto.OperationType;
import com.donnatto.transactions.dto.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Transaction {
    
    @Id
    private final UUID transactionId = UUID.randomUUID();
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private Long amount;
    private Long initialBalance;
    private Long finalBalance;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;
}
