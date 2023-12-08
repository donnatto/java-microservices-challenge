package com.donnatto.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    
    private String customerId;
    private String name;
    private Gender gender;
    private int age;
    private String dni;
    private String address;
    private String phone;
    private CustomerStatus status;
}
