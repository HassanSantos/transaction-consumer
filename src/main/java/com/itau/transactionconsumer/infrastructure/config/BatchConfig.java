//package com.itau.infrastructure.config;
//
//import com.itau.application.service.TransactionProcessorService;
//import com.itau.domain.model.Transaction;
//import com.itau.infrastructure.messaging.SqsMessageListener;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.List;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//@RequiredArgsConstructor
//public class BatchConfig {
//
//    private final TransactionProcessorService transactionProcessorService;
//    private final SqsMessageListener sqsMessageListener;
//
//    @Bean
//    public ItemReader<Transaction> transactionItemReader() {
//        return new ListItemReader<>(List.of()); // Será preenchido dinamicamente
//    }
//
//    @Bean
//    public ItemProcessor<Transaction, Transaction> transactionItemProcessor() {
//        return transaction -> {
//            transactionProcessorService.processTransaction(transaction);
//            return transaction;
//        };
//    }
//
//    @Bean
//    public ItemWriter<Transaction> transactionItemWriter() {
//        return transactions -> {
//            // O processamento já foi feito no processor
//            log.debug("Processed {} transactions", transactions.size());
//        };
//    }
//
//    @Bean
//    public Step processTransactionsStep(JobRepository jobRepository,
//                                        PlatformTransactionManager transactionManager) {
//        return new StepBuilder("processTransactionsStep", jobRepository)
//                .<Transaction, Transaction>chunk(10, transactionManager) // Processar em chunks de 10
//                .reader(transactionItemReader())
//                .processor(transactionItemProcessor())
//                .writer(transactionItemWriter())
//                .faultTolerant()
//                .retryLimit(3)
//                .retry(Exception.class)
//                .skipLimit(10)
//                .skip(Exception.class)
//                .build();
//    }
//
//    @Bean
//    public Job processTransactionsJob(JobRepository jobRepository, Step processTransactionsStep) {
//        return new JobBuilder("processTransactionsJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(processTransactionsStep)
//                .build();
//    }
//}