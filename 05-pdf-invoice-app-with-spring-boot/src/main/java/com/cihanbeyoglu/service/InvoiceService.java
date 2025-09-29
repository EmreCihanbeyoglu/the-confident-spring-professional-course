package com.cihanbeyoglu.service;


import com.cihanbeyoglu.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.rmi.NoSuchObjectException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String baseUrl;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceService(UserService userService, @Value("${baseurl}") String baseUrl, JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.baseUrl = baseUrl;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Transactional
    public List<Invoice> findAll() {
        return jdbcTemplate.query("select id, userId, pdfUrl, amount from invoices", (resultSet, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfUrl(resultSet.getString("pdfUrl"));
            invoice.setUserId(resultSet.getString("userId"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    @Transactional
    public Invoice create(String userId, Integer amount) throws NoSuchObjectException {

        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        String generatedPdfUrl = baseUrl + "/images/default/sample.pdf";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into invoices (userId, pdfUrl, amount) values (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);  //
            ps.setString(2, generatedPdfUrl);
            ps.setInt(3, amount);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);
        return invoice;
    }

}
