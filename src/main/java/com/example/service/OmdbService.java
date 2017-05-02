package com.example.service;

import com.example.config.OmdbConfig;
import com.example.remote.OmdbSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class OmdbService {
    private final OmdbConfig config;
    private final RestTemplate restTemplate;

    public OmdbService(OmdbConfig config, RestTemplate restTemplate) {
        this.config = config;
        this.restTemplate = restTemplate;
    }

    public OmdbSearchResponse searchOmdb(String query) {
        URI uri = UriComponentsBuilder.fromUriString("{host}/?s={query}")
                .buildAndExpand(config.getBaseUrl(), query)
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }
}
