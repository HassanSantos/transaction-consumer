package com.itau.transactionconsumer.adapter.out.persistence.mapper;

import com.itau.transactionconsumer.adapter.out.persistence.entity.TransactionEntity;
import com.itau.transactionconsumer.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "accountId", source = "accountId")
    TransactionEntity toEntity(Transaction transaction, UUID accountId);
}