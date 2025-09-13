package com.cihanbeyoglu.web;

import com.cihanbeyoglu.dto.InvoiceDTO;
import com.cihanbeyoglu.model.Invoice;
import com.cihanbeyoglu.service.InvoiceService;
import com.cihanbeyoglu.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

//@Controller
@RestController
public class MyFancyPdfInvoicesController {


    private final UserService userService;
    private final InvoiceService invoiceService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MyFancyPdfInvoicesController(UserService userService, InvoiceService invoiceService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.invoiceService = invoiceService;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/invoices")
//    @ResponseBody
    public List<Invoice> getInvoices() {
        return invoiceService.findAll();
    }


//    @PostMapping("/invoices")
////    @ResponseBody
//    public Invoice createInvoice(@RequestParam("user_id") String user_id, @RequestParam("amount") Integer amount) throws NoSuchObjectException {
//        return invoiceService.create(user_id, amount);
//    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody InvoiceDTO invoiceDTO) throws NoSuchObjectException {
        return invoiceService.create(invoiceDTO.getUserId(), invoiceDTO.getAmount());
    }




}
