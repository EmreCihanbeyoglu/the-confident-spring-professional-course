package com.cihanbeyoglu.service;


import com.cihanbeyoglu.model.Invoice;
import com.cihanbeyoglu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.rmi.NoSuchObjectException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String baseUrl;

    @Autowired
    public InvoiceService(UserService userService, @Value("${baseurl}") String baseUrl) {
        this.userService = userService;
        this.baseUrl = baseUrl;
    }

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) throws NoSuchObjectException {

        User user =  userService.findById(userId);
        if (user == null) {
            throw new NoSuchObjectException("User not found");
        }

        Invoice invoice = new Invoice(userId, amount, baseUrl);
        invoices.add(invoice);
        return invoice;
    }

}
