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
                .map(CustomerMapper::mapEntityToDto)
                .toList();
    }
    
    public CustomerResponseDTO getCustomer(UUID id) {
        return customerRepository.findActiveCustomerById(id)
                .map(CustomerMapper::mapEntityToDto)
                .orElseThrow(CustomerNotFoundException::new);
    }
    
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO requestDTO) {
        Customer customer = new Customer();
        customer.setStatus(CustomerStatus.ACTIVE);
        CustomerMapper.mapDtoToEntity(requestDTO, customer);
        Customer savedCustomer = customerRepository.saveAndFlush(customer);
        return CustomerMapper.mapEntityToDto(savedCustomer);
    }
    
    public CustomerResponseDTO updateCustomer(UUID id, CustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findActiveCustomerById(id).orElseThrow(CustomerNotFoundException::new);
        CustomerMapper.mapDtoToEntity(requestDTO, customer);
        Customer updatedCustomer = customerRepository.saveAndFlush(customer);
        return CustomerMapper.mapEntityToDto(updatedCustomer);
    }
    
    public CustomerResponseDTO patchCustomer(UUID id, PatchCustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findActiveCustomerById(id).orElseThrow(CustomerNotFoundException::new);
        CustomerMapper.mapPatchDtoToEntity(requestDTO, customer);
        Customer updatedCustomer = customerRepository.saveAndFlush(customer);
        return CustomerMapper.mapEntityToDto(updatedCustomer);
    }
    
    public void deleteCustomer(UUID id) {
        Customer customer = customerRepository.findActiveCustomerById(id).orElseThrow(CustomerNotFoundException::new);
        customer.setStatus(CustomerStatus.INACTIVE);
        customerRepository.save(customer);
    }
}
