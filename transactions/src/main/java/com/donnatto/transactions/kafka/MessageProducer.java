package com.donnatto.transactions.kafka;

import com.donnatto.transactions.dto.AccountEvent;
import com.donnatto.transactions.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageProducer {
    
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    public void sendAccountEvent(String topic, Account account) {
        AccountEvent accountEvent = AccountEvent.builder()
                .accountId(account.getAccountId())
                .customerId(account.getCustomerId().toString())
                .build();
        kafkaTemplate.send(topic, accountEvent);
    }
}
