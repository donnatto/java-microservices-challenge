package com.donnatto.customers.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerResponseDTO {
    
    private final String customerId;
    private final String name;
    private final Gender gender;
    private final int age;
    private final String dni;
    private final String address;
    private final String phone;
    private final CustomerStatus status;
}
