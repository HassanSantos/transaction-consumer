//package com.itau.transactionconsumer.adapter.out.persistence.mapper;
//
//import com.itau.transactionconsumer.domain.model.Account;
//import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface AccountEntityMapper {
//
//    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);
//
//    @Mapping(target = "balance.amount", source = "balance.amount")
//    @Mapping(target = "balance.currency", source = "balance.currency")
//    Account toDomain(AccountEntity entity);
//
//    @Mapping(target = "balance.amount", source = "balance.amount")
//    @Mapping(target = "balance.currency", source = "balance.currency")
//    AccountEntity toEntity(Account domain);
//}