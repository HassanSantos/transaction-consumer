//package com.itau.transactionconsumer.adapter.out.persistence.mapper;
//
//import com.itau.transactionconsumer.adapter.out.persistence.entity.TransactionEntity;
//import com.itau.transactionconsumer.domain.model.Account;
//import com.itau.transactionconsumer.domain.model.Transaction;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface TransactionMapper {
//
//    @Mapping(source = "timestamp", target = "createdAt")
//    @Mapping(target = "account", ignore = true)
//        // Você precisará tratar essa conversão separadamente
//    TransactionEntity toEntity(Transaction transaction, Account account);
//
//    // Adicione também o método inverso se necessário
//    @Mapping(source = "createdAt", target = "timestamp")
//    Transaction toDomain(TransactionEntity entity);
//}