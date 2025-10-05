package com.cihanbeyoglu.repository;

import com.cihanbeyoglu.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
