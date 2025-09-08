package com.cihanbeyoglu.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.cihanbeyoglu.service", "com.cihanbeyoglu.web"})
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class MyFancyPdfInvoicesApplicationConfiguration {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
