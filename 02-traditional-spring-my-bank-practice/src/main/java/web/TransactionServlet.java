package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.MyBankAppConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TransactionService;

import java.io.IOException;
import java.util.List;

public class TransactionServlet extends HttpServlet {

    private TransactionService transactionService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBankAppConfig.class);

        // for predestroy lifecycle callback
        context.registerShutdownHook();

        this.transactionService = context.getBean(TransactionService.class);
        this.mapper = context.getBean(ObjectMapper.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        if ( request.getRequestURI().equals("/transaction")) {
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String reference = request.getParameter("reference");

            response.setContentType("application/json; charset=utf-8");

            Transaction tx = transactionService.create(amount, reference);

            String json = mapper.writeValueAsString(tx);

            response.getWriter().print(json);

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/transaction")) {
            List<Transaction> transactions =  transactionService.getTransactions();
            response.setContentType("application/json; charset=utf-8");

            String json = mapper.writeValueAsString(transactions);
            response.getWriter().print(json);

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
