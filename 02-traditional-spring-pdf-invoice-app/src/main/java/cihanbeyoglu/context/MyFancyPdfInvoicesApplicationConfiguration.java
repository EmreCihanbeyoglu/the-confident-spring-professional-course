package cihanbeyoglu.context;

import cihanbeyoglu.service.InvoiceService;
import cihanbeyoglu.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public InvoiceService invoiceService(UserService userService) {
        return new InvoiceService(userService);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
