//// src/main/java/com/itau/account/balance/adapter/in/messaging/mapper/AccountMessageMapper.java
//package com.itau.transactionconsumer.adapter.in.messaging.mapper;
//
//import com.itau.account.balance.adapter.in.messaging.dto.TransactionMessage.AccountData;
//import com.itau.account.balance.adapter.in.messaging.dto.TransactionMessage.BalanceData;
//import com.itau.account.balance.domain.model.Account;
//import com.itau.account.balance.domain.model.Balance;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//@Mapper(componentModel = "spring")
//public interface AccountMessageMapper {
//
//    @Mapping(source = "status", target = "status", qualifiedByName = "stringToAccountStatus")
//    @Mapping(source = "balance", target = "balance")
//    Account toAccount(AccountData accountData);
//
//    @Mapping(source = "amount", target = "amount")
//    @Mapping(source = "currency", target = "currency")
//    Balance toBalanceDomain(BalanceData balanceData);
//
//    @Named("stringToAccountStatus")
//    default Account.AccountStatus stringToAccountStatus(String status) {
//        if (status == null) {
//            return null;
//        }
//        return switch (status.toUpperCase()) {
//            case "ENABLED" -> Account.AccountStatus.ENABLED;
//            case "DISABLED" -> Account.AccountStatus.DISABLED;
//            default -> throw new IllegalArgumentException("Status desconhecido: " + status);
//        };
//    }
//}