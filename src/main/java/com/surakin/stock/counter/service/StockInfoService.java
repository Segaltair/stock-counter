package com.surakin.stock.counter.service;

import com.surakin.stock.counter.dto.Quote;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class StockInfoService {
    private final RestTemplate restTemplate;
    private final Params params;

    public StockInfoService(RestTemplateBuilder restTemplateBuilder, Params params) {
        this.restTemplate = restTemplateBuilder.build();
        this.params = params;
    }

    public Quote getQuoteBySymbol(String name) {
        String latestPriceUrl = MessageFormat.format("https://cloud.iexapis.com/stable/stock/{0}/quote?token=" + params.getToken(), name);
        String sectorUrl = MessageFormat.format("https://cloud.iexapis.com/stable/stock/{0}/company?token=" + params.getToken(), name);
        Quote quote = this.restTemplate.getForObject(latestPriceUrl, Quote.class, name);
        Quote sector = this.restTemplate.getForObject(sectorUrl, Quote.class, name);
        if (quote == null || sector == null)
            throw new RuntimeException("Не найдена акция " + name);
        quote.setSector(sector.getSector());
        return quote;
    }
}
