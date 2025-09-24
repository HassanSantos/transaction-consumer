//package com.itau.transactionconsumer.adapter.out.persistence.repository;
//
//import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
//import jakarta.persistence.LockModeType;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Lock;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//import java.util.UUID;
//
//public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
//
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @Query("SELECT a FROM AccountEntity a WHERE a.id = :id")
//    Optional<AccountEntity> findByIdWithLock(@Param("id") UUID id);
//
//    Optional<AccountEntity> findByOwner(String owner);
//}