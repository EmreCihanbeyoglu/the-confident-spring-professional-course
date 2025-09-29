package com.cihanbeyoglu.service;



import com.cihanbeyoglu.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;
    private final String slogan;

    @Autowired
    public TransactionService(JdbcTemplate jdbcTemplate, @Value("${bank.slogan}") String slogan) {
        this.jdbcTemplate = jdbcTemplate;
        this.slogan = slogan;
    }


    @Transactional
    public Transaction create (Integer amount, String reference) {
        System.out.println("Is a database transaction open? = " + org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive());
        Transaction transaction = new Transaction(amount, reference, this.slogan);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into transactions (id, amount, createdDate, reference, slogan) values (?, ?, ?, ?, ?)");
            ps.setString(1, transaction.getId());
            ps.setInt(2, transaction.getAmount());
            ps.setString(3, transaction.getCreatedDate().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(4, transaction.getReference());
            ps.setString(5, transaction.getSlogan());
            return ps;
        });

        return transaction;
    }

    @Transactional
    public List<Transaction> getTransactions() {
        System.out.println("Is a database transaction open? = " + org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive());
        return jdbcTemplate.query("select id, amount, createdDate, reference, slogan from transactions", (resultSet, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setId(resultSet.getString("id"));
            transaction.setAmount(resultSet.getInt("amount"));
            transaction.setReference(resultSet.getString("reference"));
            String dateStr = resultSet.getString("createdDate");
            transaction.setCreatedDate(LocalDateTime.parse(dateStr, java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            transaction.setSlogan(resultSet.getString("slogan"));
            return transaction;
        });
    }

}
