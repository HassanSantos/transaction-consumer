package com.itau.transactionconsumer.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account", schema = "transaction_schema", indexes = {
        @Index(name = "idx_account_owner", columnList = "owner"),
        @Index(name = "idx_account_updated", columnList = "updatedAt")
})
@AttributeOverride(name = "balance.amount", column = @Column(name = "amount"))
@AttributeOverride(name = "balance.currency", column = @Column(name = "currency"))
public class AccountEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID owner;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Embedded
    private Balance balance;

    public enum AccountStatus {
        ENABLED, DISABLED
    }
}