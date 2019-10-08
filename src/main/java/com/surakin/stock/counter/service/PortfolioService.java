package com.surakin.stock.counter.service;

import com.surakin.stock.counter.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для подсчета текущей стоимости портфеля
 */
@Service
@Slf4j
public class PortfolioService {

    @Value("${token}")
    private String token;

    /**
     * Посчитать суммарную стоимость и распределение по секторам
     *
     * @param request список акций
     * @return общая стоимость и распределение акций по секторам
     */
    public Response getPortfolio(Request request) {
        BigDecimal totalValue = BigDecimal.ZERO;
        // Распределение акций по секторам
        Map<String, BigDecimal> allocations = new HashMap<>();

        // Находим суммарную стоимость акций для каждого сектора
        for (Stock stock : request.getStocks()) {
            Quote quote = getQuoteBySymbol(stock.getSymbol());
            String sector = quote.getSector();
            BigDecimal assetValue = quote.getLatestPrice().multiply(BigDecimal.valueOf(stock.getVolume()));
            totalValue = totalValue.add(assetValue);
            // Добавляем новый сектор, либо объединяем стоимость, если такой уже есть
            if (allocations.get(sector) == null) {
                allocations.put(sector, assetValue);
            } else {
                allocations.put(sector, allocations.get(sector).add(assetValue));
            }
        }

        List<Portfolio> result = new ArrayList<>();
        BigDecimal finalTotalValue = totalValue;
        log.info("Общая стоимость портфеля: {}", finalTotalValue);
        // Считаем пропорции и заполняем инфу о каждом секторе
        allocations.forEach((sector, assetValue) -> {
            BigDecimal proportion = assetValue.divide(finalTotalValue, 3, RoundingMode.HALF_UP);
            log.info("Сектор: {}. Стоимость: {}. Объем сектора от общей стоимости портфеля {}", sector, assetValue, proportion);
            result.add(new Portfolio(sector, assetValue.setScale(0, RoundingMode.HALF_UP), proportion));
        });

        log.info("Портфель рассчитан");
        return new Response(totalValue.toBigInteger().intValue(), result);
    }

    /**
     * Достать акцию по названию
     *
     * @param name название акции
     * @return информация об акции
     */
    private Quote getQuoteBySymbol(String name) {
        String latestPriceUrl = MessageFormat.format("https://cloud.iexapis.com/stable/stock/{0}/quote?token={1}", name, token);
        String sectorUrl = MessageFormat.format("https://cloud.iexapis.com/stable/stock/{0}/company?token={1}", name, token);
        RestTemplate restTemplate = new RestTemplate();
        // Достать цену акции
        Quote quote = restTemplate.getForObject(latestPriceUrl, Quote.class);
        // Достать сектор акции
        Quote sector = restTemplate.getForObject(sectorUrl, Quote.class);
        if (quote == null || sector == null) {
            log.info("Акция {} не найдена", name);
            throw new RuntimeException("Не найдена акция " + name);
        }
        quote.setSector(sector.getSector());
        log.info("Акция {} найдена. Цена: {}. Сектор: {}", name, quote.getLatestPrice(), quote.getSector());
        return quote;
    }
}
