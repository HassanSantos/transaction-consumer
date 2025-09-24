package com.itau.transactionconsumer.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TransactionMessage {

    @JsonProperty("transaction")
    private TransactionData transaction;

    @JsonProperty("account")
    private AccountData account;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionData {
        private String id;
        private String type;
        private BigDecimal amount;
        private String currency;
        private String status;
        private Long timestamp;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountData {
        private UUID id;
        private UUID owner;
        private String createdAt;
        private String status;
        private BalanceData balance;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class BalanceData {
            private BigDecimal amount;
            private String currency;
        }
    }
}