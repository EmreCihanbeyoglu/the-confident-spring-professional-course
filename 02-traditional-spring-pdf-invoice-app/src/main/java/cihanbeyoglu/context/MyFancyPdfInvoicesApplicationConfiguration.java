package cihanbeyoglu.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"cihanbeyoglu.service"})
@PropertySource("classpath:application.properties")
public class MyFancyPdfInvoicesApplicationConfiguration {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
