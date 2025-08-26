package cihanbeyoglu.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"cihanbeyoglu.service"})
public class MyFancyPdfInvoicesApplicationConfiguration {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
