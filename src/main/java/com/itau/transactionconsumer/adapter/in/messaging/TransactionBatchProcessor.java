package com.itau.transactionconsumer.adapter.in.messaging;

import com.itau.transactionconsumer.adapter.in.messaging.dto.TransactionMessage;
import com.itau.transactionconsumer.adapter.in.messaging.mapper.TransactionMapper;
import com.itau.transactionconsumer.application.port.in.ProcessTransactionUseCase;
import com.itau.transactionconsumer.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionBatchProcessor {

    private final ProcessTransactionUseCase processTransactionUseCase;
    private final TransactionMapper transactionMapper;

    public void processTransactionMessage(TransactionMessage message) {
        try {

            Transaction transaction = transactionMapper.toTransaction(message.transaction());
            processTransactionUseCase.processTransaction(transaction);

            log.debug("Transaction processed successfully in batch: {}", transaction.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to process transaction in batch", e);
        }
    }
}