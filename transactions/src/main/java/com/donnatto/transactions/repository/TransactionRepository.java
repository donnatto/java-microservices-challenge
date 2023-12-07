package com.donnatto.transactions.repository;

import com.donnatto.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("select t from TRANSACTION t where t.account.accountId = ?1")
    List<Transaction> findAllByAccountId(Long accountId);
}
