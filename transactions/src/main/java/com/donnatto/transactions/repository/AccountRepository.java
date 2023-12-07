package com.donnatto.transactions.repository;

import com.donnatto.transactions.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from ACCOUNT a where a.accountStatus = 'ACTIVE'")
    List<Account> findAllActiveAccounts();
    @Query("select a from ACCOUNT a where a.accountId = ?1 and a.accountStatus = 'ACTIVE'")
    Optional<Account> findActiveAccountById(Long id);
}
