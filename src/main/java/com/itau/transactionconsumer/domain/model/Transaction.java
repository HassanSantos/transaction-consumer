package com.itau.transactionconsumer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Transaction {
    private String id;
    private String accountId;
    private TransactionType type;
    private BigDecimal amount;
    private String currency;
    private TransactionStatus status;
    private Instant timestamp;
    private Instant processedAt;
    private Long version;

    public enum TransactionType {
        CREDIT, DEBIT
    }

    public enum TransactionStatus {
        APPROVED, REJECTED, PENDING
    }

    public boolean isValid() {
        return id != null && accountId != null && type != null
                && amount != null && amount.compareTo(BigDecimal.ZERO) > 0
                && status != null && timestamp != null;
    }

    public boolean isApproved() {
        return status == TransactionStatus.APPROVED;
    }
}