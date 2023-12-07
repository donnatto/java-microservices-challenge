package com.donnatto.transactions.kafka;

import com.donnatto.transactions.dto.AccountCreationStatus;
import com.donnatto.transactions.dto.AccountStatus;
import com.donnatto.transactions.dto.CustomerStatus;
import com.donnatto.transactions.dto.UpdateAccountEvent;
import com.donnatto.transactions.entity.Account;
import com.donnatto.transactions.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class MessageConsumer {
    
    private final AccountRepository accountRepository;
    
    @KafkaListener(topics = "update-account-event-topic", groupId = "update-account-event-group")
    public void processUpdateAccountEvents(UpdateAccountEvent event) {
        Optional<Account> optAccount = accountRepository.findById(event.getAccountId());
        optAccount.ifPresent(account -> {
            CustomerStatus status = event.getStatus();
            if (status == CustomerStatus.INACTIVE) {
                account.setCreationStatus(AccountCreationStatus.CREATION_FAILED);
                account.setAccountStatus(AccountStatus.INACTIVE);
            } else if (status == CustomerStatus.ACTIVE) {
                account.setCustomerName(event.getCustomerName());
                account.setCreationStatus(AccountCreationStatus.CREATION_COMPLETED);
                account.setAccountStatus(AccountStatus.ACTIVE);
            }
            accountRepository.save(account);
        });
    }
}
