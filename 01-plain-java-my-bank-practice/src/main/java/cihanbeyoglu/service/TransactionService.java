package cihanbeyoglu.service;

import cihanbeyoglu.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    List<Transaction> transactions = new ArrayList<Transaction>();

    public Transaction create (Integer amount, String reference) {
        Transaction transaction = new Transaction(amount, reference);
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}
