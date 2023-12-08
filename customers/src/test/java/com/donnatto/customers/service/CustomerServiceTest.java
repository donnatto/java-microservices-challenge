package com.donnatto.customers.service;

import com.donnatto.customers.data.TestData;
import com.donnatto.customers.dto.CustomerRequestDTO;
import com.donnatto.customers.dto.CustomerResponseDTO;
import com.donnatto.customers.dto.CustomerStatus;
import com.donnatto.customers.dto.PatchCustomerRequestDTO;
import com.donnatto.customers.entity.Customer;
import com.donnatto.customers.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    
    @Mock
    private CustomerRepository repositoryMock;
    
    @InjectMocks
    private CustomerService service;
    
    @Test
    void shouldGetAllCustomers() {
        List<Customer> testCustomers = TestData.getCustomers();
        Mockito.when(repositoryMock.findAllActiveCustomers()).thenReturn(testCustomers);
    
        List<CustomerResponseDTO> responseDTOS = service.getCustomers();
        Mockito.verify(repositoryMock, Mockito.times(1)).findAllActiveCustomers();
        for (int i = 0; i < responseDTOS.size(); i++) {
            CustomerResponseDTO currentDto = responseDTOS.get(i);
            Customer currentCustomer = testCustomers.get(i);
            assertEquals(currentCustomer.getCustomerId().toString(), currentDto.getCustomerId());
            assertEquals(currentCustomer.getName(), currentDto.getName());
            assertEquals(currentCustomer.getGender(), currentDto.getGender());
            assertEquals(currentCustomer.getAge(), currentDto.getAge());
            assertEquals(currentCustomer.getDni(), currentDto.getDni());
            assertEquals(currentCustomer.getAddress(), currentDto.getAddress());
            assertEquals(currentCustomer.getPhone(), currentDto.getPhone());
            assertEquals(currentCustomer.getStatus(), currentDto.getStatus());
        }
    }
    
    @Test
    void shouldGetCustomerById() {
        Customer testCustomer = TestData.getCustomer1();
        Mockito.when(repositoryMock.findActiveCustomerById(any(UUID.class))).thenReturn(Optional.of(testCustomer));
    
        CustomerResponseDTO responseDTO = service.getCustomer(testCustomer.getCustomerId());
        Mockito.verify(repositoryMock, Mockito.times(1)).findActiveCustomerById(any(UUID.class));
        assertEquals(testCustomer.getCustomerId().toString(), responseDTO.getCustomerId());
        assertEquals(testCustomer.getName(), responseDTO.getName());
        assertEquals(testCustomer.getGender(), responseDTO.getGender());
        assertEquals(testCustomer.getAge(), responseDTO.getAge());
        assertEquals(testCustomer.getDni(), responseDTO.getDni());
        assertEquals(testCustomer.getAddress(), responseDTO.getAddress());
        assertEquals(testCustomer.getPhone(), responseDTO.getPhone());
        assertEquals(testCustomer.getStatus(), responseDTO.getStatus());
    }
    
    @Test
    void shouldSaveCustomer() {
        CustomerRequestDTO requestDTO = TestData.createRequestDto();
        Customer testCustomer = TestData.getCustomerFromDTO(requestDTO);
        Mockito.when(repositoryMock.saveAndFlush(any(Customer.class))).thenReturn(testCustomer);
    
        service.saveCustomer(requestDTO);
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(repositoryMock, Mockito.times(1)).saveAndFlush(any(Customer.class));
        Mockito.verify(repositoryMock).saveAndFlush(captor.capture());
        
        assertEquals(requestDTO.getDni(), captor.getValue().getDni());
        assertEquals(requestDTO.getName(), captor.getValue().getName());
        assertEquals(requestDTO.getGender(), captor.getValue().getGender().toString());
        assertEquals(requestDTO.getAge(), captor.getValue().getAge());
        assertEquals(requestDTO.getAddress(), captor.getValue().getAddress());
        assertEquals(requestDTO.getPhone(), captor.getValue().getPhone());
        assertEquals(requestDTO.getPassword(), captor.getValue().getPassword());
        assertEquals(CustomerStatus.ACTIVE, captor.getValue().getStatus());
        
    }
    
    @Test
    void shouldUpdateCustomer() {
        Customer testCustomer = TestData.getCustomer1();
        CustomerRequestDTO updateRequestDto = TestData.createRequestDto();
        Mockito.when(repositoryMock.findActiveCustomerById(any(UUID.class))).thenReturn(Optional.of(testCustomer));
        Mockito.when(repositoryMock.saveAndFlush(any(Customer.class))).thenReturn(testCustomer);
        
        service.updateCustomer(testCustomer.getCustomerId(), updateRequestDto);
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(repositoryMock, Mockito.times(1)).findActiveCustomerById(any(UUID.class));
        Mockito.verify(repositoryMock, Mockito.times(1)).saveAndFlush(any(Customer.class));
        Mockito.verify(repositoryMock).saveAndFlush(captor.capture());
        
        assertEquals(updateRequestDto.getDni(), captor.getValue().getDni());
        assertEquals(updateRequestDto.getName(), captor.getValue().getName());
        assertEquals(updateRequestDto.getGender(), captor.getValue().getGender().toString());
        assertEquals(updateRequestDto.getAge(), captor.getValue().getAge());
        assertEquals(updateRequestDto.getAddress(), captor.getValue().getAddress());
        assertEquals(updateRequestDto.getPhone(), captor.getValue().getPhone());
        assertEquals(updateRequestDto.getPassword(), captor.getValue().getPassword());
    }
    
    @Test
    void shouldPatchCustomer() {
        Customer testCustomer = TestData.getCustomer1();
        PatchCustomerRequestDTO patchDto = TestData.createPatchDto();
        Mockito.when(repositoryMock.findActiveCustomerById(any(UUID.class))).thenReturn(Optional.of(testCustomer));
        Mockito.when(repositoryMock.saveAndFlush(any(Customer.class))).thenReturn(testCustomer);
        
        service.patchCustomer(testCustomer.getCustomerId(), patchDto);
        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(repositoryMock, Mockito.times(1)).findActiveCustomerById(any(UUID.class));
        Mockito.verify(repositoryMock, Mockito.times(1)).saveAndFlush(any(Customer.class));
        Mockito.verify(repositoryMock).saveAndFlush(captor.capture());
        
        assertEquals(patchDto.getAddress().orElse(""), captor.getValue().getAddress());
    }
    
    @Test
    void shouldDeleteCustomer() {
        Customer testCustomer = TestData.getCustomer1();
        Mockito.when(repositoryMock.findActiveCustomerById(any(UUID.class))).thenReturn(Optional.of(testCustomer));
        
        service.deleteCustomer(testCustomer.getCustomerId());
        Mockito.verify(repositoryMock, Mockito.times(1)).save(testCustomer);
        assertEquals(CustomerStatus.INACTIVE, testCustomer.getStatus());
    }
}