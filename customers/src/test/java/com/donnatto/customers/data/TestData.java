package com.donnatto.customers.data;

import com.donnatto.customers.dto.CustomerRequestDTO;
import com.donnatto.customers.dto.CustomerStatus;
import com.donnatto.customers.dto.Gender;
import com.donnatto.customers.dto.PatchCustomerRequestDTO;
import com.donnatto.customers.entity.Customer;

import java.util.List;
import java.util.Optional;

public class TestData {
    
    private TestData() {}
    
    public static List<Customer> getCustomers() {
        Customer customer1 = getCustomer1();
        Customer customer2 = getCustomer2();
        return List.of(customer1, customer2);
    }
    
    public static Customer getCustomerFromDTO(CustomerRequestDTO requestDTO) {
        Customer customer = new Customer();
        customer.setDni(requestDTO.getDni());
        customer.setName(requestDTO.getName());
        customer.setGender(Gender.valueOf(requestDTO.getGender()));
        customer.setAge(requestDTO.getAge());
        customer.setAddress(requestDTO.getAddress());
        customer.setPhone(requestDTO.getPhone());
        customer.setPassword(requestDTO.getPassword());
        return customer;
    }
    
    public static Customer getCustomer1() {
        Customer customer1 = new Customer();
        customer1.setDni("12345678");
        customer1.setName("Adam Smith");
        customer1.setGender(Gender.MALE);
        customer1.setAge(30);
        customer1.setAddress("Rio Parana 110");
        customer1.setPhone("988883322");
        customer1.setPassword("secret");
        customer1.setStatus(CustomerStatus.ACTIVE);
        return customer1;
    }
    
    public static Customer getCustomer2() {
        Customer customer2 = new Customer();
        customer2.setDni("87654321");
        customer2.setName("Jane Jones");
        customer2.setGender(Gender.FEMALE);
        customer2.setAge(25);
        customer2.setAddress("Javier Prado 2444");
        customer2.setPhone("923455432");
        customer2.setPassword("hello");
        customer2.setStatus(CustomerStatus.ACTIVE);
        return customer2;
    }
    
    public static CustomerRequestDTO createRequestDto() {
        return new CustomerRequestDTO(
                "12345678",
                "Pedro Perez",
                "MALE",
                20,
                "Acapulco 111",
                "977664511",
                "secret"
        );
    }
    
    public static PatchCustomerRequestDTO createPatchDto() {
        return new PatchCustomerRequestDTO(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Jr Paz 394"),
                Optional.empty(),
                Optional.empty()
        );
    }
}
