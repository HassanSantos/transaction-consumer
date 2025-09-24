//package com.itau.transactionconsumer.adapter.in.messaging;
//
//import com.itau.account.balance.adapter.in.messaging.dto.TransactionMessage;
//import com.itau.account.balance.adapter.in.messaging.mapper.AccountMessageMapper;
//import com.itau.account.balance.adapter.in.messaging.mapper.TransactionMapper;
//import com.itau.account.balance.application.port.in.ProcessTransactionUseCase;
//import com.itau.account.balance.domain.model.Account;
//import com.itau.account.balance.domain.model.Transaction;
//import io.awspring.cloud.sqs.annotation.SqsListener;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Log4j2
//public class TransactionConsumer {
//
//    private final ProcessTransactionUseCase processTransactionUseCase;
//    private final AccountMessageMapper accountMessageMapper;
//    private final TransactionMapper transactionMapper;
//
//    @SqsListener(value = "${aws.sqs.queue.transactions}")
//    public void receiveTransaction(Message<TransactionMessage> message) {
//        try {
//            TransactionMessage payload = message.getPayload();
//            log.info("Received transaction: {}", payload.transaction().id());
//
//            Transaction transaction = convertToDomain(payload);
//            Account account = convertAccount(payload);
//            processTransactionUseCase.processTransaction(transaction, account);
//
//            log.info("Successfully processed transaction: {}", payload.transaction().id());
//        } catch (Exception e) {
//            log.error("Error processing transaction: {}", e.getMessage(), e);
//            throw e;
//        }
//    }
//
//    private Transaction convertToDomain(TransactionMessage message) {
//        return transactionMapper.toTransaction(message.transaction());
//    }
//
//    private Account convertAccount(TransactionMessage message) {
//
//        return accountMessageMapper.toAccount(message.account());
//    }
//}