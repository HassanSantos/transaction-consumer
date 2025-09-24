package com.itau.transactionconsumer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private UUID id;
    private UUID owner;
    private Instant createdAt;
    private AccountStatus status;
    private Balance balance;
    private Instant lastUpdated;
    private Integer version;

    public enum AccountStatus {
        ENABLED, DISABLED
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Balance {
        private BigDecimal amount;
        private String currency;

        public Balance add(BigDecimal value) {
            return Balance.builder()
                    .amount(this.amount.add(value))
                    .currency(this.currency)
                    .build();
        }

        public Balance subtract(BigDecimal value) {
            return Balance.builder()
                    .amount(this.amount.subtract(value))
                    .currency(this.currency)
                    .build();
        }
    }

    public boolean canProcessTransaction() {
        return status == AccountStatus.ENABLED;
    }

    public void updateBalance(Transaction transaction, BigDecimal newBalance) {

        if (transaction.getType() == Transaction.TransactionType.CREDIT) {
            this.balance = balance.add(transaction.getAmount());
        } else {
            this.balance = balance.subtract(transaction.getAmount());
        }

        this.lastUpdated = Instant.now();
        this.version = (this.version == null) ? 1 : this.version + 1;
    }
}