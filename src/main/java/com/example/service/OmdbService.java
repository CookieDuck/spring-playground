package com.example.service;

import com.example.remote.OmdbSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class OmdbService {
    //TODO inject from constructor!
    private final RestTemplate restTemplate = new RestTemplate();

    public OmdbSearchResponse searchOmdb(String query) {
        URI uri = UriComponentsBuilder.fromUriString("http://www.omdbapi.com/?s={query}")
                .buildAndExpand(query)
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }
}
