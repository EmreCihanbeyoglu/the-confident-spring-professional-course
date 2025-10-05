package com.cihanbeyoglu.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    private String id;

    @NotBlank
    private String userId;

    private String pdfUrl;

    @Min(10)
    @Max(999999)
    private Integer amount;
}
