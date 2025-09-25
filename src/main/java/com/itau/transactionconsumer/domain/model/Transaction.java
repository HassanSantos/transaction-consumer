package com.itau.transactionconsumer.domain.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {

    private String id;
    private String type;
    private BigDecimal amount;
    private String currency;
    private String status;
//    TODO: ALTERAR PARA DATE TIME
    private Long timestamp;
    @OneToMany(fetch = FetchType.LAZY)
    private Account account;
}
