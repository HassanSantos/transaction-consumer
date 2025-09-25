package com.itau.transactionconsumer.application.service;

import com.itau.transactionconsumer.adapter.out.persistence.mapper.AccountMapper;
import com.itau.transactionconsumer.adapter.out.persistence.mapper.TransactionMapper;
import com.itau.transactionconsumer.adapter.out.persistence.repository.AccountRepository;
import com.itau.transactionconsumer.adapter.out.persistence.repository.TransactionRepository;
import com.itau.transactionconsumer.application.port.in.ProcessTransactionUseCase;
import com.itau.transactionconsumer.domain.model.Account;
import com.itau.transactionconsumer.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService implements ProcessTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    @Override
    public void processTransaction(Transaction transaction, Account account) {

        try {
            var accountEntity = accountMapper.toEntity(account);
            accountRepository.save(accountEntity);
            var transactione = transactionMapper.toEntity(transaction, accountEntity.getId());
            transactionRepository.save(transactione);
        } catch (Exception e) {
            log.error("ERROR TO SAVE");
            log.error(e.getMessage(), e);

//            TODO: TRATAR MELHOR EXCEPTION
        }

    }


}