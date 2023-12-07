package com.donnatto.transactions.repository;

import com.donnatto.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("select t from TRANSACTION t where t.account.accountId = ?1 order by t.timestamp desc")
    List<Transaction> findAllByAccountId(Long accountId);
    @Query("select t from TRANSACTION t order by t.timestamp desc")
    List<Transaction> findAllOrderedByTimestamp();
    @Query("select t from TRANSACTION t where t.account.accountId = ?1 and t.timestamp between ?2 and ?3 order by t.timestamp desc")
    List<Transaction> findAllByAccountIdAndDateRange(Long accountId, LocalDateTime from, LocalDateTime to);
}
