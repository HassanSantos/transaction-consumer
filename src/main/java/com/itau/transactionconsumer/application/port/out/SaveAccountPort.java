package com.itau.transactionconsumer.application.port.out;

import com.itau.transactionconsumer.domain.model.Account;

public interface SaveAccountPort {
    Account saveAccount(Account account);

}