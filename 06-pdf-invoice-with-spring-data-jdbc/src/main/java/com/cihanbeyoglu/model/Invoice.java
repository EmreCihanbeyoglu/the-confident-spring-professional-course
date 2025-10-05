package com.cihanbeyoglu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("invoices")
public class Invoice {

    @Id
    @Column("ID")
    private String id;

    @Column("USER_ID")
    private String userId;

    @Column("PDF_URL")
    private String pdfUrl;

    @Column("AMOUNT")
    private Integer amount;
}
