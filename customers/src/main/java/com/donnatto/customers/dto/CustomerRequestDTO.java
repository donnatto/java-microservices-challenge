package com.donnatto.customers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerRequestDTO {
    @NotBlank(message = "The customer DNI is required")
    private final String dni;
    @NotBlank(message = "The customer name is required")
    private final String name;
    @NotBlank(message = "The customer gender is required")
    private final String gender;
    @NotNull(message = "The customer age is required")
    private final int age;
    @NotBlank(message = "The customer address is required")
    private final String address;
    @NotBlank(message = "The customer phone is required")
    private final String phone;
    @NotBlank(message = "The customer password is required")
    private final String password;
}
