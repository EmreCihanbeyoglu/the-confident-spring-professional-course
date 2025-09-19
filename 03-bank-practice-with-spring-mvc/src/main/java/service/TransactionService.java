package service;


import model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionService {

    private final String slogan;

    @Autowired
    public TransactionService(@Value("${bank.slogan}") String slogan) {
        this.slogan = slogan;
    }

    List<Transaction> transactions = new ArrayList<>();

    public Transaction create (Integer amount, String reference) {
        Transaction transaction = new Transaction(amount, reference, this.slogan);
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}
