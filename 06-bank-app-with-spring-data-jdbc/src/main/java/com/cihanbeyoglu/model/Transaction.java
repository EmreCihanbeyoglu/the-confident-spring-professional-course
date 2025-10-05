package com.cihanbeyoglu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table("TRANSACTIONS")
public class Transaction {

    @Id
    @Column("ID")
    private String id;

    @Column("AMOUNT")
    private Integer amount;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column("CREATED_DATE")
    private LocalDateTime createdDate;

    @Column("REFERENCE")
    private String reference;

    @Column("SLOGAN")
    private String slogan;
}
