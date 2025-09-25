package com.itau.transactionconsumer.adapter.in.messaging;

import com.itau.transactionconsumer.adapter.in.messaging.dto.TransactionMessage;
import com.itau.transactionconsumer.adapter.in.messaging.mapper.AccountMessageMapper;
import com.itau.transactionconsumer.adapter.in.messaging.mapper.TransactionMessageMapper;
import com.itau.transactionconsumer.application.port.in.ProcessTransactionUseCase;
import com.itau.transactionconsumer.domain.model.Account;
import com.itau.transactionconsumer.domain.model.Transaction;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);
    private final ProcessTransactionUseCase processTransactionUseCase;
    private final SqsTemplate sqsTemplate;
    private final AccountMessageMapper accountMessageMapper;
    private final TransactionMessageMapper transactionMessageMapper;

    @SqsListener(value = "${aws.sqs.queue.transactions}")
    @Async("asyncExecutor")
    public void receiveTransaction(Message<TransactionMessage> message) {
        try {

            logger.info("testando se funciona -------------", LocalDateTime.now());
            TransactionMessage payload = message.getPayload();
            logger.info("Received transaction: {}", payload.transaction().id());

            Transaction transaction = transactionMessageMapper.toTransactionModel(payload.transaction());
            Account account = convertAccount(payload);
            processTransactionUseCase.processTransaction(transaction, account);

            logger.info("Successfully processed transaction: {}", payload.transaction().id());
        } catch (Exception e) {
            logger.error("Error processing transaction: {}", e.getMessage(), e);
//            TODO: IMPLEMENTAR DLQ
        }
    }

    private Transaction convertToDomain(TransactionMessage payload) {
        return transactionMessageMapper.toTransactionModel(payload.transaction());
    }

    private Account convertAccount(TransactionMessage message) {

        return accountMessageMapper.toDomain(message.account());
    }
}