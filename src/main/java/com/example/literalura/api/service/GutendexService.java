package com.example.literalura.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GutendexService {
    private static final String BASE_URL = "https://gutendex.com/books";

    public String searchBooksByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("search", title)
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
