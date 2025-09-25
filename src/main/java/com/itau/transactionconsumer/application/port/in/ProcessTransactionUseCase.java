package com.itau.transactionconsumer.application.port.in;

import com.itau.transactionconsumer.domain.model.Account;
import com.itau.transactionconsumer.domain.model.Transaction;

public interface ProcessTransactionUseCase {
    void processTransaction(Transaction transaction, Account account);
}
