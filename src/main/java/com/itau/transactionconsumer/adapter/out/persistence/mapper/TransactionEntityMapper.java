package com.itau.transactionconsumer.adapter.out.persistence.mapper;

import com.itau.transactionconsumer.adapter.out.persistence.entity.AccountEntity;
import com.itau.transactionconsumer.adapter.out.persistence.entity.TransactionEntity;
import com.itau.transactionconsumer.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionEntityMapper {

    TransactionEntity toEntity(Transaction domain, AccountEntity account);
}