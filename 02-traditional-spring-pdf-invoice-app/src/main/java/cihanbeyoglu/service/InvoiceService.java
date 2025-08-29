package cihanbeyoglu.service;

import cihanbeyoglu.model.Invoice;
import cihanbeyoglu.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

    @PostConstruct
    public void init() {
        System.out.println("this is going to be executed after the bean is constructed with its dependencies");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("this is going to be executed before the bean is destroyed");
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
