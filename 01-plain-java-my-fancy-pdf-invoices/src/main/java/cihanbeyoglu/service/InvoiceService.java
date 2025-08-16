package cihanbeyoglu.service;

import cihanbeyoglu.context.Application;
import cihanbeyoglu.model.Invoice;
import cihanbeyoglu.model.User;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    private final UserService userService;

    public InvoiceService(UserService userService) {
        this.userService = userService;
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

        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }

}
