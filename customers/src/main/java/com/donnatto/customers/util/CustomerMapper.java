package com.donnatto.customers.util;

import com.donnatto.customers.dto.CustomerRequestDTO;
import com.donnatto.customers.dto.CustomerResponseDTO;
import com.donnatto.customers.dto.Gender;
import com.donnatto.customers.dto.PatchCustomerRequestDTO;
import com.donnatto.customers.entity.Customer;

public class CustomerMapper {
    
    private CustomerMapper() {}
    
    public static void mapDtoToEntity(CustomerRequestDTO requestDTO, Customer customer) {
        customer.setName(requestDTO.getName());
        customer.setPassword(requestDTO.getPassword());
        customer.setGender(Gender.valueOf(requestDTO.getGender()));
        customer.setAge(requestDTO.getAge());
        customer.setDni(requestDTO.getDni());
        customer.setAddress(requestDTO.getAddress());
        customer.setPhone(requestDTO.getPhone());
    }
    
    public static void mapPatchDtoToEntity(
            PatchCustomerRequestDTO requestDTO, Customer customer) {
        requestDTO.getName().ifPresent(customer::setName);
        requestDTO.getPassword().ifPresent(customer::setPassword);
        requestDTO.getGender().ifPresent(gender -> customer.setGender(Gender.valueOf(gender)));
        requestDTO.getAge().ifPresent(customer::setAge);
        requestDTO.getDni().ifPresent(customer::setDni);
        requestDTO.getAddress().ifPresent(customer::setAddress);
        requestDTO.getPhone().ifPresent(customer::setPhone);
    }
    
    public static CustomerResponseDTO mapEntityToDto(Customer customer) {
        return CustomerResponseDTO.builder()
                .customerId(customer.getCustomerId().toString())
                .name(customer.getName())
                .gender(customer.getGender())
                .age(customer.getAge())
                .dni(customer.getDni())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();
    }
}
