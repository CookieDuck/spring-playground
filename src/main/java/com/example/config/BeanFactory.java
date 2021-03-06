package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanFactory {
    @Bean
    public RestTemplate makeRestTemplate() {
        return new RestTemplate();
    }
}
