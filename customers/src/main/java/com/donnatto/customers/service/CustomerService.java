package com.donnatto.customers.service;

import com.donnatto.customers.dto.CustomerRequestDTO;
import com.donnatto.customers.dto.CustomerResponseDTO;
import com.donnatto.customers.dto.CustomerStatus;
import com.donnatto.customers.dto.PatchCustomerRequestDTO;
import com.donnatto.customers.entity.Customer;
import com.donnatto.customers.exceptions.CustomerNotFoundException;
import com.donnatto.customers.repository.CustomerRepository;
import com.donnatto.customers.util.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    public List<CustomerResponseDTO> getCustomers() {
        return customerRepository.findAllActiveCustomers().stream()
                .map(CustomerMapper::mapCustomerEntityToCustomerResponseDto)
                .toList();
    }
    
    public CustomerResponseDTO getCustomer(UUID id) {
        return customerRepository.findActiveCustomerById(id)
                .map(CustomerMapper::mapCustomerEntityToCustomerResponseDto)
                .orElseThrow(CustomerNotFoundException::new);
    }
    
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO requestDTO) {
        Customer customer = new Customer();
        customer.setStatus(CustomerStatus.ACTIVE);
        CustomerMapper.mapCustomerRequestDtoToCustomerEntity(requestDTO, customer);
        Customer savedCustomer = customerRepository.saveAndFlush(customer);
        return CustomerMapper.mapCustomerEntityToCustomerResponseDto(savedCustomer);
    }
    
    public CustomerResponseDTO patchCustomer(UUID id, PatchCustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        CustomerMapper.mapPatchCustomerRequestDtoToCustomerEntity(requestDTO, customer);
        Customer updatedCustomer = customerRepository.saveAndFlush(customer);
        return CustomerMapper.mapCustomerEntityToCustomerResponseDto(updatedCustomer);
    }
    
    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customer.setStatus(CustomerStatus.INACTIVE);
        customerRepository.save(customer);
    }
}
