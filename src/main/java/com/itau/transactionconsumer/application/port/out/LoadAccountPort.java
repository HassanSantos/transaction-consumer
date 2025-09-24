package com.itau.transactionconsumer.application.port.out;

import com.itau.transactionconsumer.domain.model.Account;
import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> loadAccount(String accountId);
    Optional<Account> loadAccountWithLock(String accountId);
}