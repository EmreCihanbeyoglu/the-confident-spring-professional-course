package com.cihanbeyoglu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Transaction {
    private String id;

    private Integer amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    private String reference;

    private String slogan;

    public Transaction(Integer amount, String reference, String slogan) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.createdDate = LocalDateTime.now();
        this.reference = reference;
        this.slogan = slogan;
    }
}
