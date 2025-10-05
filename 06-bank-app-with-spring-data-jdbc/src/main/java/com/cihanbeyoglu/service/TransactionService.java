package com.cihanbeyoglu.service;



import com.cihanbeyoglu.dto.TransactionDto;
import com.cihanbeyoglu.mapper.TransactionMapper;
import com.cihanbeyoglu.model.Transaction;
import com.cihanbeyoglu.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.StreamSupport;


@Component
public class TransactionService {

    private final String slogan;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(@Value("${bank.slogan}") String slogan, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.slogan = slogan;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Transactional
    public TransactionDto create (Integer amount, String reference) {
        System.out.println("Is a database transaction open? = " + org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReference(reference);
        transaction.setSlogan(this.slogan);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.mapToDto(savedTransaction);
    }

    @Transactional
    public List<TransactionDto> getTransactions() {
        System.out.println("Is a database transaction open? = " + org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive());

        Iterable<Transaction> transactions = transactionRepository.findAll();
        return StreamSupport.stream(transactions.spliterator(), false)
                        .map(transactionMapper::mapToDto)
                                .toList();
    }

}
