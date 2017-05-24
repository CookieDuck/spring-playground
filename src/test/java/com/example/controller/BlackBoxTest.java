package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
@RunWith(SpringRunner.class)
public class BlackBoxTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testCalculate() {
        URI uri = UriComponentsBuilder.fromUriString("/math/calculate?operation={op}&x={x}&y={y}")
                .buildAndExpand("multiply", "7", "6")
                .toUri();
        
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        assertEquals("7 * 6 = 42", result.getBody());
    }
}
