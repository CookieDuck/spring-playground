package com.example.config;

import com.example.service.WordCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanFactory {
    @Bean
    public WordCounter makeWordCounter(WordCountConfig wordCountConfig) {
        return new WordCounter(wordCountConfig);
    }

    @Bean
    public RestTemplate makeRestTemplate() {
        return new RestTemplate();
    }
}
