//package com.itau.transactionconsumer.application.service;
//
//import com.itau.transactionconsumer.application.port.in.ProcessTransactionUseCase;
//import com.itau.transactionconsumer.application.port.out.LoadAccountPort;
//import com.itau.transactionconsumer.application.port.out.SaveAccountPort;
//import com.itau.transactionconsumer.domain.model.Account;
//import com.itau.transactionconsumer.domain.model.Transaction;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.orm.ObjectOptimisticLockingFailureException;
//import org.springframework.retry.annotation.Backoff;
//import org.springframework.retry.annotation.Retryable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class TransactionProcessingService implements ProcessTransactionUseCase {
//
//    private final LoadAccountPort loadAccountPort;
//    private final SaveAccountPort saveAccountPort;
//
//    @Override
//    @Transactional
//    @Retryable(retryFor = ObjectOptimisticLockingFailureException.class,
//            maxAttempts = 3,
//            backoff = @Backoff(delay = 100))
//    public void processTransaction(Transaction transaction) {
//        log.info("Processing transaction: {}", transaction.getId());
//
//        if (saveAccountPort.existsTransaction(transaction.getId())) {
//            log.warn("Transaction {} already processed", transaction.getId());
//            return;
//        }
//
//        Account account = loadAccountPort.loadAccountWithLock(transaction.getAccountId())
//                .orElseGet(() -> createNewAccountFromTransaction(transaction));
//
////        validateTransaction(transaction, account);
//        updateAccountBalance(transaction, account);
//
//        transaction.setProcessedAt(Instant.now());
//        saveAccountPort.saveTransaction(transaction);
//
//        log.info("Transaction {} processed successfully", transaction.getId());
//    }
//
//    private Account createNewAccountFromTransaction(Transaction transaction) {
//        log.info("Creating new account from transaction: {}", transaction.getAccountId());
//        // Esta seria uma lógica para criar conta baseada na transação
//        // Na prática, precisaríamos de mais dados da conta
//        throw new IllegalStateException("Account creation from transaction not implemented");
//    }
//
////    private void validateTransaction(Transaction transaction, Account account) {
////        if (!account.isEnabled()) {
////            throw new IllegalStateException("Account is disabled: " + account.getId());
////        }
////
////        if (transaction.isDebit() && transaction.isApproved()) {
////            BigDecimal newBalance = account.getBalance().getAmount().subtract(transaction.getAmount());
////            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
////                throw new IllegalStateException("Insufficient funds for account: " + account.getId());
////            }
////        }
////    }
//
//    private void updateAccountBalance(Transaction transaction, Account account) {
//        BigDecimal newBalance = calculateNewBalance(transaction, account);
//        account.updateBalance(transaction, newBalance);
//        saveAccountPort.saveAccount(account);
//    }
//
//    private BigDecimal calculateNewBalance(Transaction transaction, Account account) {
//        BigDecimal currentBalance = account.getBalance().getAmount();
//
////        if (transaction.isApproved()) {
////            return transaction.isCredit()
////                    ? currentBalance.add(transaction.getAmount())
////                    : currentBalance.subtract(transaction.getAmount());
////        }
//        return currentBalance;
//    }
//}