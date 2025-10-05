package com.cihanbeyoglu.repository;

import com.cihanbeyoglu.model.Invoice;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {

    // custom SQL queries by using @Query annotation
    @Query("SELECT ID, PDF_URL, USER_ID, AMOUNT FROM \"invoices\" WHERE USER_ID = :userId")
    Iterable<Invoice> findByUserId(@Param("userId") String userId);
}
