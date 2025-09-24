package com.itau.transactionconsumer.config;

import com.itau.transactionconsumer.adapter.in.messaging.TransactionBatchProcessor;
import com.itau.transactionconsumer.adapter.in.messaging.dto.TransactionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final TransactionBatchProcessor transactionBatchProcessor;

    @Bean
    public Job transactionProcessingJob(JobRepository jobRepository, Step processTransactionStep) {
        return new JobBuilder("transactionProcessingJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(processTransactionStep)
                .build();
    }

    @Bean
    public Step processTransactionStep(JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager) {
        return new StepBuilder("processTransactionStep", jobRepository)
                .<TransactionMessage, Void>chunk(10, transactionManager)
                .reader(transactionItemReader())
                .processor(transactionItemProcessor())
                .writer(transactionItemWriter())
                .faultTolerant()
                .retryLimit(3)
                .retry(DataAccessException.class) // Exceções de acesso a dados
                .retry(Exception.class) // Exceções genéricas (como fallback)
                .skipLimit(10) // Permite pular até 10 itens com erro
                .skip(Exception.class)
                .build();
    }

    @Bean
    public ListItemReader<TransactionMessage> transactionItemReader() {
        return new ListItemReader<>(List.of()); // Será populado pelo SQS listener
    }

    @Bean
    public ItemProcessor<TransactionMessage, Void> transactionItemProcessor() {
        return message -> {
            transactionBatchProcessor.processTransactionMessage(message);
            return null;
        };
    }

    @Bean
    public ItemWriter<Void> transactionItemWriter() {
        return items -> {
            // Batch commit handled by Spring Batch
            log.debug("Processed {} transactions in batch", items.size());
        };
    }
}