package com.itau.transactionconsumer.adapter.in.messaging.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionMessage(
        TransactionData transaction,
        AccountData account
) {
    public record TransactionData(
            String id,
            String type,
            BigDecimal amount,
            String currency,
            String status,
            Instant timestamp
    ) {
    }

    public record AccountData(
            UUID id,
            String owner,
            Instant createdAt,
            String status,
            BalanceData balance
    ) {
    }

    public record BalanceData(
            BigDecimal amount,
            String currency
    ) {
    }
}