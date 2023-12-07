package com.donnatto.customers.kafka;

import com.donnatto.customers.dto.AccountEvent;
import com.donnatto.customers.dto.CustomerStatus;
import com.donnatto.customers.dto.UpdateAccountEvent;
import com.donnatto.customers.entity.Customer;
import com.donnatto.customers.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class MessageConsumer {
    
    private final CustomerRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    
    @KafkaListener(topics = "account-event-topic", groupId = "account-event-group")
    public void processAccountEvents(AccountEvent accountEvent) {
        UpdateAccountEvent updateAccountEvent = validateCustomer(accountEvent);
        kafkaTemplate.send("update-account-event-topic", updateAccountEvent);
        
    }
    
    private UpdateAccountEvent validateCustomer(AccountEvent accountEvent) {
        Optional<Customer> optCustomer = repository
                .findActiveCustomerById(UUID.fromString(accountEvent.getCustomerId()));
        if (optCustomer.isPresent()) {
            return UpdateAccountEvent.builder()
                    .accountId(accountEvent.getAccountId())
                    .customerName(optCustomer.get().getName())
                    .status(CustomerStatus.ACTIVE)
                    .build();
        } else {
            return UpdateAccountEvent.builder()
                    .accountId(accountEvent.getAccountId())
                    .status(CustomerStatus.INACTIVE)
                    .build();
        }
    }
}
