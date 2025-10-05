package com.cihanbeyoglu.controller;

import com.cihanbeyoglu.dto.InvoiceDTO;
import com.cihanbeyoglu.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public InvoiceDTO createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) throws NoSuchObjectException {
        return invoiceService.create(invoiceDTO.getUserId(), invoiceDTO.getAmount());
    }

    @GetMapping("/invoices/user/{userId}")
    public List<InvoiceDTO> getInvoicesForGivenUser(@PathVariable("userId") String userId) {
        return invoiceService.findByUserId(userId);
    }

}
