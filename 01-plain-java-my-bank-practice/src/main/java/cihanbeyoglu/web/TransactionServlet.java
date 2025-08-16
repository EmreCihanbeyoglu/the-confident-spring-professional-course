package cihanbeyoglu.web;

import cihanbeyoglu.context.Application;
import cihanbeyoglu.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        if ( request.getRequestURI().equals("/transaction")) {
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String reference = request.getParameter("reference");

            response.setContentType("application/json; charset=utf-8");

            Transaction tx = Application.transactionService.create(amount, reference);

            String json = Application.mapper.writeValueAsString(tx);

            response.getWriter().print(json);

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/transaction")) {
            List<Transaction> transactions =  Application.transactionService.getTransactions();
            response.setContentType("application/json; charset=utf-8");

            String json = Application.mapper.writeValueAsString(transactions);
            response.getWriter().print(json);

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
