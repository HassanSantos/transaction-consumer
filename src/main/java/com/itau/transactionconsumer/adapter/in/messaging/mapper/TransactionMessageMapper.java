package com.itau.transactionconsumer.adapter.in.messaging.mapper;

import com.itau.transactionconsumer.adapter.in.messaging.dto.TransactionMessage;
import com.itau.transactionconsumer.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMessageMapper {

    Transaction toTransactionModel(TransactionMessage.TransactionData transactionMessage);

}
