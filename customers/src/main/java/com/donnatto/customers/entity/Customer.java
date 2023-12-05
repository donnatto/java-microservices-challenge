package com.donnatto.customers.entity;

import com.donnatto.customers.dto.CustomerStatus;
import com.donnatto.customers.dto.Person;
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
@Entity(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Customer extends Person {
    
    @Id
    private final UUID customerId = UUID.randomUUID();
    private String password;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
}
