package com.itau.transactionconsumer.application.service;

import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
import com.itau.transactionconsumer.adapter.out.persistence.mapper.TransactionEntityMapper;
import com.itau.transactionconsumer.adapter.out.persistence.repository.TransactionRepository;
import com.itau.transactionconsumer.application.port.in.ProcessTransactionUseCase;
import com.itau.transactionconsumer.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionProcessorService implements ProcessTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public void processTransaction(Transaction transaction) {
        try {
            log.debug("Processing transaction: {}", transaction.getId());

            // Validar transação
            validateTransaction(transaction);

            // Persistir transação
//            TODO: VERIFICAR
            transactionRepository.save(transactionEntityMapper.toEntity(transaction, AccountEntity.builder().build()));

            log.info("Transaction processed successfully: {}", transaction.getId());

        } catch (Exception e) {
            log.error("Error processing transaction: {}", transaction.getId(), e);
            throw e;
        }
    }

    private void validateTransaction(Transaction transaction) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }
    }
}