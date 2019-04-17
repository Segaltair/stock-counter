package com.surakin.stock.counter;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {
    private final RestTemplate restTemplate;

    public MyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Stock getStockBySymbol(String name) {
//        String name = stock.getCompanyName();
        return this.restTemplate.getForObject("https://api.iextrading.com/1.0/stock/{name}/quote/", Stock.class, name);
    }
}
