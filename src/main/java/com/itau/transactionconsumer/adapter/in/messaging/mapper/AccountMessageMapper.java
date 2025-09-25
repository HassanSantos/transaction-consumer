package com.itau.transactionconsumer.adapter.in.messaging.mapper;

import com.itau.transactionconsumer.adapter.in.messaging.dto.TransactionMessage.AccountData;
import com.itau.transactionconsumer.adapter.in.messaging.dto.TransactionMessage.BalanceData;
import com.itau.transactionconsumer.domain.model.Account;
import com.itau.transactionconsumer.domain.model.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface AccountMessageMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "stringToAccountStatus")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "stringToInstant")
    Account toDomain(AccountData accountData);

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "currency", target = "currency")
    Balance toBalanceDomain(BalanceData balanceData);

    @Named("stringToAccountStatus")
    default Account.AccountStatus stringToAccountStatus(String status) {
        if (status == null) {
            return null;
        }
        return switch (status.toUpperCase()) {
            case "ENABLED" -> Account.AccountStatus.ENABLED;
            case "DISABLED" -> Account.AccountStatus.DISABLED;
            default -> throw new IllegalArgumentException("Status desconhecido: " + status);
        };
    }


    @Named("stringToInstant")
    default Instant stringToInstant(String createdAt) {
        if (createdAt == null) {
            return null;
        }
        long seconds = Long.parseLong(createdAt);
        return Instant.ofEpochSecond(seconds);

    }
}