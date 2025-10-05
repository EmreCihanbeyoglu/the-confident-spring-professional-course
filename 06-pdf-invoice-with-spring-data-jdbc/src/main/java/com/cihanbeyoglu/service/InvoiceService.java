package com.cihanbeyoglu.service;


import com.cihanbeyoglu.dto.InvoiceDTO;
import com.cihanbeyoglu.mapper.InvoiceMapper;
import com.cihanbeyoglu.model.Invoice;
import com.cihanbeyoglu.repository.InvoiceRepository;
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
import java.util.stream.StreamSupport;

@Component
public class InvoiceService {

    private final String baseUrl;
    private final JdbcTemplate jdbcTemplate; // no need if you use Spring-Data-JDBC
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceService(@Value("${baseurl}") String baseUrl, JdbcTemplate jdbcTemplate, InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.baseUrl = baseUrl;
        this.jdbcTemplate = jdbcTemplate;
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }
    @Transactional
    public List<InvoiceDTO> findAll() {
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(), false)
                .map(invoiceMapper::mapToDto)
                .toList();


//        return jdbcTemplate.query("select id, userId, pdfUrl, amount from invoices", (resultSet, rowNum) -> {
//            Invoice invoice = new Invoice();
//            invoice.setId(resultSet.getObject("id").toString());
//            invoice.setPdfUrl(resultSet.getString("pdfUrl"));
//            invoice.setUserId(resultSet.getString("userId"));
//            invoice.setAmount(resultSet.getInt("amount"));
//            return invoice;
//        });
    }

    @Transactional
    public InvoiceDTO create(String userId, Integer amount) throws NoSuchObjectException {

        String generatedPdfUrl = baseUrl + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);

        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.mapToDto(savedInvoice);


//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection
//                    .prepareStatement("insert into invoices (userId, pdfUrl, amount) values (?, ?, ?)",
//                            Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, userId);  //
//            ps.setString(2, generatedPdfUrl);
//            ps.setInt(3, amount);
//            return ps;
//        }, keyHolder);
//
//        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
//                : null;
//
//        Invoice invoice = new Invoice();
//        invoice.setId(uuid);
//        invoice.setPdfUrl(generatedPdfUrl);
//        invoice.setAmount(amount);
//        invoice.setUserId(userId);
//        return invoice;
    }

    public List<InvoiceDTO> findByUserId(String userId) {
        return StreamSupport.stream(invoiceRepository.findByUserId(userId).spliterator(), false)
                .map(invoiceMapper::mapToDto)
                .toList();
    }
}
