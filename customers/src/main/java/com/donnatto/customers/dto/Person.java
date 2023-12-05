package com.donnatto.customers.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {
    private String dni;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private String address;
    private String phone;
}
