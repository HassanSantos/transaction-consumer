package com.itau.transactionconsumer.adapter.in.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
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
            Long timestamp
    ) {
    }

    public record AccountData(
            UUID id,
            String owner,
            @JsonProperty("created_at")
            String createdAt,
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