//package com.itau.transactionconsumer.domain.service;
//
//import com.itau.transactionconsumer.application.port.in.ProcessTransactionUseCase;
//import com.itau.transactionconsumer.domain.model.Account;
//import com.itau.transactionconsumer.domain.model.Transaction;
//import com.itau.transactionconsumer.domain.model.TransactionMessage;
//import com.itau.transactionconsumer.domain.ports.output.AccountRepository;
//import com.itau.transactionconsumer.domain.ports.output.TransactionRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//
//@Slf4j
//@RequiredArgsConstructor
//public class TransactionProcessingService implements ProcessTransactionUseCase {
//
//    private final AccountRepository accountRepository;
//    private final TransactionRepository transactionRepository;
//
//    @Override
//    public void processTransaction(TransactionMessage message) {
//        log.info("Processing transaction: {} for account: {}",
//                message.getTransaction().getId(), message.getAccount().getId());
//
//
//        // Process account update with optimistic locking
//        processAccountUpdate(message);
//
//        // Save transaction
//        saveTransaction(message);
//    }
//
//    private void processAccountUpdate(TransactionMessage message) {
//        Account account = accountRepository.findById(message.getAccount().getId())
//                .orElseGet(() -> createNewAccount(message));
//
//        // Apply optimistic locking
//        Account updatedAccount = updateAccountBalance(account, message);
//        accountRepository.saveWithLock(updatedAccount);
//    }
//
//    private Account createNewAccount(TransactionMessage message) {
//        log.info("Creating new account: {}", message.getAccount().getId());
//        return Account.builder()
//                .id(message.getAccount().getId())
//                .owner(message.getAccount().getOwner())
//                .createdAt(Instant.ofEpochSecond(Long.parseLong(message.getAccount().getCreatedAt())))
//                .status(Account.AccountStatus.valueOf(message.getAccount().getStatus()))
//                .balance(Account.Balance.builder()
//                        .amount(message.getAccount().getBalance().getAmount())
//                        .currency(message.getAccount().getBalance().getCurrency())
//                        .build())
//                .version(0)
//                .build();
//    }
//
//    private Account updateAccountBalance(Account account, TransactionMessage message) {
//        if (!account.canProcessTransaction()) {
//            log.warn("Account {} cannot process transactions. Status: {}",
//                    account.getId(), account.getStatus());
//            return account;
//        }
//
////        if (message.getTransaction().) {
////            BigDecimal newBalance = calculateNewBalance(account, message);
////            account.getBalance().setAmount(newBalance);
////            log.debug("Updated balance for account {}: {}", account.getId(), newBalance);
////        }
//
//        account.setVersion(account.getVersion() + 1);
//        return account;
//    }
//
//    private BigDecimal calculateNewBalance(Account account, TransactionMessage message) {
//        BigDecimal currentBalance = account.getBalance().getAmount();
//        BigDecimal transactionAmount = message.getTransaction().getAmount();
//
//        return message.getTransaction().getType() == Transaction.TransactionType.CREDIT.toString() ?
//                currentBalance.add(transactionAmount) :
//                currentBalance.subtract(transactionAmount);
//    }
//
//    private void saveTransaction(TransactionMessage message) {
//        Transaction transaction = Transaction.builder()
//                .id(message.getTransaction().getId())
//                .accountId(message.getAccount().getId())
//                .type(Transaction.TransactionType.valueOf(message.getTransaction().getType()))
//                .amount(message.getTransaction().getAmount())
//                .currency(message.getTransaction().getCurrency())
//                .status(Transaction.TransactionStatus.valueOf(message.getTransaction().getStatus()))
//                .timestamp(Instant.ofEpochMicro(message.getTransaction().getTimestamp()))
//                .processedAt(Instant.now())
//                .version(0L)
//                .build();
//
//        transactionRepository.save(transaction);
//        log.info("Transaction {} saved successfully", transaction.getId());
//    }
//}