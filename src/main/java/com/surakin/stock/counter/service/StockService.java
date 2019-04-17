package com.surakin.stock.counter.service;

import com.surakin.stock.counter.dto.Stock;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {
    private final RestTemplate restTemplate;

    public StockService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Stock getStockBySymbol(String name) {
        return this.restTemplate.getForObject("https://api.iextrading.com/1.0/stock/{name}/quote/", Stock.class, name);
    }
}
