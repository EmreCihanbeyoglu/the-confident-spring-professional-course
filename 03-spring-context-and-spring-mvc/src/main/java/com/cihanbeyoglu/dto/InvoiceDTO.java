package com.cihanbeyoglu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private String id;

    private String userId;

    private String pdfUrl;

    private Integer amount;
}
