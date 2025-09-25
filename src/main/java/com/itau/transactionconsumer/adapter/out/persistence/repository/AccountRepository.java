package com.itau.transactionconsumer.adapter.out.persistence.repository;

import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

//    @Query("SELECT a FROM AccountEntity a WHERE a.id = :accountId")
//    Optional<AccountEntity> findByAccountId(String accountId);

//    boolean existsById(String accountId);
}