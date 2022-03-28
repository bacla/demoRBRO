package com.example.demoRBRO.service.impl;


import com.example.demoRBRO.service.RatesService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RatesServiceImpl implements RatesService {

    @Override
    @Cacheable(value = "rates")
    public String getRates() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://api.exchangeratesapi.io/latest?access_key=b1744fad4506dc47e3b6cb05f40790cb",String.class);
//        String response = restTemplate.getForObject("https://open.er-api.com/v6/latest/RON",String.class);
//        String url = "http://api.exchangeratesapi.io/latest";
//        URI uri = URI.create(url);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("access_key", "b1744fad4506dc47e3b6cb05f40790cb");
//        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET,headers,String.class);

        if (response != null) {
            return response;
        } else {
            return null;
        }
    }
}
