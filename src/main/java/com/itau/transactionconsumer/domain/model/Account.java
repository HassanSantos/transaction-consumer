package com.itau.transactionconsumer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private UUID id;
    private String owner;
    private Instant createdAt;
    private AccountStatus status;
    private Balance balance;

    public enum AccountStatus {
        ENABLED, DISABLED
    }
}