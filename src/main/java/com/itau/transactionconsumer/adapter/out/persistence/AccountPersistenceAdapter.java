//package com.itau.transactionconsumer.adapter.out.persistence;
//
//import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
//import com.itau.transactionconsumer.adapter.out.persistence.entity.TransactionEntity;
//import com.itau.transactionconsumer.adapter.out.persistence.mapper.AccountEntityMapper;
//import com.itau.transactionconsumer.adapter.out.persistence.mapper.TransactionEntityMapper;
//import com.itau.transactionconsumer.adapter.out.persistence.repository.AccountRepository;
//import com.itau.transactionconsumer.adapter.out.persistence.repository.TransactionRepository;
//import com.itau.transactionconsumer.application.port.out.LoadAccountPort;
//import com.itau.transactionconsumer.application.port.out.SaveAccountPort;
//import com.itau.transactionconsumer.domain.model.Account;
//import com.itau.transactionconsumer.domain.model.Transaction;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {
//
//    private final AccountRepository accountRepository;
//    private final TransactionRepository transactionRepository;
//    private final AccountEntityMapper accountMapper;
//    private final TransactionEntityMapper transactionMapper;
//
//
//    @Override
//    public Optional<Account> loadAccountWithLock(String accountId) {
//        return accountRepository.findByIdWithLock(UUID.fromString(accountId))
//                .map(accountMapper::toDomain);
//    }
//
//    @Override
//    public Account saveAccount(Account account) {
//        AccountEntity entity = accountMapper.toEntity(account);
//        AccountEntity saved = accountRepository.save(entity);
//        return accountMapper.toDomain(saved);
//    }
//
//    @Override
//    public void saveTransaction(Transaction transaction) {
//        TransactionEntity entity = transactionMapper.toEntity(transaction);
//        transactionRepository.save(entity);
//    }
//
//    @Override
//    public boolean existsTransaction(String transactionId) {
//        return transactionRepository.existsById(UUID.fromString(transactionId));
//    }
//}