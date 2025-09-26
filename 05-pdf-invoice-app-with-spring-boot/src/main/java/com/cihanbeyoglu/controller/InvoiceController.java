package com.cihanbeyoglu.controller;

import com.cihanbeyoglu.dto.InvoiceDTO;
import com.cihanbeyoglu.mapper.InvoiceMapper;
import com.cihanbeyoglu.model.Invoice;
import com.cihanbeyoglu.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceMapper invoiceMapper) {
        this.invoiceService = invoiceService;
        this.invoiceMapper = invoiceMapper;
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.findAll().stream()
                .map(invoice -> invoiceMapper.mapToDto(invoice))
                .toList();
    }

    @PostMapping("/invoices")
    public InvoiceDTO createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) throws NoSuchObjectException {
        Invoice invoice = invoiceService.create(invoiceDTO.getUserId(), invoiceDTO.getAmount());
        return invoiceMapper.mapToDto(invoice);
    }

}
