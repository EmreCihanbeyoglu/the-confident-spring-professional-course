package com.cihanbeyoglu.controller;



import com.cihanbeyoglu.dto.TransactionDto;
import com.cihanbeyoglu.mapper.TransactionMapper;
import com.cihanbeyoglu.model.Transaction;
import com.cihanbeyoglu.service.TransactionService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }


    @PostMapping("/transaction")
    public TransactionDto createTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        Transaction transaction = transactionService.create(transactionDto.getAmount(), transactionDto.getReference());
        return transactionMapper.mapToDto(transaction);
    }

    @GetMapping("/transactions")
    public List<TransactionDto> getTransactions() {
        return transactionService.getTransactions().stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }


}
