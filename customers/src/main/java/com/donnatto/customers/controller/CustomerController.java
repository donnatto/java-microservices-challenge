package com.donnatto.customers.controller;

import com.donnatto.customers.dto.CustomerRequestDTO;
import com.donnatto.customers.dto.CustomerResponseDTO;
import com.donnatto.customers.dto.PatchCustomerRequestDTO;
import com.donnatto.customers.service.CustomerService;
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
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    
    private final CustomerService service;
    
    @GetMapping
    public List<CustomerResponseDTO> getCustomers() {
        return service.getCustomers();
    }
    
    @GetMapping("/{customerId}")
    public CustomerResponseDTO getCustomer(@PathVariable String customerId) {
        return service.getCustomer(UUID.fromString(customerId));
    }
    
    @PostMapping
    public CustomerResponseDTO createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        return service.saveCustomer(requestDTO);
    }
    
    @PatchMapping("/{customerId}")
    public CustomerResponseDTO updateCustomer(
            @PathVariable String customerId, @RequestBody PatchCustomerRequestDTO requestDTO) {
        return service.patchCustomer(UUID.fromString(customerId), requestDTO);
    }
    
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable String customerId) {
        service.deleteCustomer(UUID.fromString(customerId));
    }
}
