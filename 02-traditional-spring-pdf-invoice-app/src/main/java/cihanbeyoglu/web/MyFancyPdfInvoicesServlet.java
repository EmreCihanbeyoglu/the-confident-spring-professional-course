package cihanbeyoglu.web;


import cihanbeyoglu.context.MyFancyPdfInvoicesApplicationConfiguration;
import cihanbeyoglu.model.Invoice;
import cihanbeyoglu.service.InvoiceService;
import cihanbeyoglu.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;


public class MyFancyPdfInvoicesServlet extends HttpServlet {


    private UserService userService;
    private InvoiceService invoiceService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);

        this.userService = context.getBean(UserService.class);
        this.invoiceService = context.getBean(InvoiceService.class);
        this.objectMapper = context.getBean(ObjectMapper.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getRequestURI().equals("/invoices")) {
            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            response.setContentType("application/json; charset=utf-8");

            String json = objectMapper.writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getRequestURI().equals("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                            "</body>\n" +
                            "</html>");
        } else if(request.getRequestURI().equals("/invoices")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = invoiceService.findAll();
            response.getWriter().print(objectMapper.writeValueAsString(invoices));
        }
    }

}
