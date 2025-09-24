package com.itau.transactionconsumer.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "balance.amount", column = @Column(name = "amount")),
        @AttributeOverride(name = "balance.currency", column = @Column(name = "currency"))
})

@Table(name = "account"
        , schema = "transaction_schema")
@Entity
public class AccountEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID owner;

    @Column(nullable = false)
    private Long createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Embedded
    private Balance balance;

    @OneToMany(mappedBy = "account")
    private List<TransactionEntity> transactionEntity;

    public enum AccountStatus {
        ENABLED, DISABLED
    }
}