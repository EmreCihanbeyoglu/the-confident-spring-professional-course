package com.cihanbeyoglu.web;

import com.cihanbeyoglu.dto.InvoiceDTO;
import com.cihanbeyoglu.model.Invoice;
import com.cihanbeyoglu.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
public class MyFancyPdfInvoicesController {

    private final InvoiceService invoiceService;

    @Autowired
    public MyFancyPdfInvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) throws NoSuchObjectException {
        return invoiceService.create(invoiceDTO.getUserId(), invoiceDTO.getAmount());
    }
}
