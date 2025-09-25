package com.itau.transactionconsumer.adapter.out.persistence.mapper;

import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
import com.itau.transactionconsumer.domain.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "domainStatusToEntityStatus")
    AccountEntity toEntity(Account account);
//
//    @Mapping(source = "status", target = "status", qualifiedByName = "entityStatusToDomainStatus")
//    @Mapping(source = "balance", target = "balance")
//    Account toDomain(AccountEntity accountEntity);
//
//    @Mapping(source = "amount", target = "amount")
//    @Mapping(source = "currency", target = "currency")
//    BalanceEntity toBalanceEntity(Balance balance);
//
//    @Mapping(source = "amount", target = "amount")
//    @Mapping(source = "currency", target = "currency")
//    Balance toBalanceDomain(BalanceEntity balanceEntity);

    @Named("domainStatusToEntityStatus")
    default AccountEntity.AccountStatus domainStatusToEntityStatus(Account.AccountStatus status) {
        if (status == null) {
            return null;
        }
        return switch (status) {
            case ENABLED -> AccountEntity.AccountStatus.ENABLED;
            case DISABLED -> AccountEntity.AccountStatus.DISABLED;
        };
    }

//    @Named("entityStatusToDomainStatus")
//    default Account.AccountStatus entityStatusToDomainStatus(AccountEntity.AccountStatus status) {
//        if (status == null) {
//            return null;
//        }
//        return switch (status) {
//            case ENABLED -> Account.AccountStatus.ENABLED;
//            case DISABLED -> Account.AccountStatus.DISABLED;
//        };
//    }
}