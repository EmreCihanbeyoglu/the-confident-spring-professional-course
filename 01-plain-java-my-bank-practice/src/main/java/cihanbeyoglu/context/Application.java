package cihanbeyoglu.context;

import cihanbeyoglu.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Application {

    public static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static TransactionService transactionService = new TransactionService();

}
