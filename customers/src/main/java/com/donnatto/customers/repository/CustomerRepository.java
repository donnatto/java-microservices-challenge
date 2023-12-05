package com.donnatto.customers.repository;

import com.donnatto.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("select c from CUSTOMER c where c.status = 'ACTIVE'")
    List<Customer> findAllActiveCustomers();
    @Query("select c from CUSTOMER c where c.customerId = ?1 and c.status = 'ACTIVE'")
    Optional<Customer> findActiveCustomerById(UUID id);
}
