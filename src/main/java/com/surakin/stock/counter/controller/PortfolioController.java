package com.surakin.stock.counter.controller;

import com.surakin.stock.counter.dto.ApiRequest;
import com.surakin.stock.counter.dto.ApiResponse;
import com.surakin.stock.counter.dto.Portfolio;
import com.surakin.stock.counter.dto.Stock;
import com.surakin.stock.counter.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PortfolioController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/portfolio", consumes = "application/json")
    public ResponseEntity<ApiResponse> baseUrlRedirect(@RequestBody ApiRequest apiRequest) {
        List<Stock> stocks = getStocksBySymbol(apiRequest);

        Double value = getTotalValue(stocks);

        List<Portfolio> portfolios = new ArrayList<>();

        stocks.stream()
                .collect(Collectors.groupingBy(Stock::getSector))
                .forEach((s, p) -> {
                    Double sumAssetValue = getSectorAssetValue(p);
                    portfolios.add(new Portfolio(s, sumAssetValue, sumAssetValue / value));
                });

        ApiResponse apiResponse = new ApiResponse(value, portfolios);

        return ResponseEntity.ok(apiResponse);
    }

    private List<Stock> getStocksBySymbol(ApiRequest apiRequest) {
        List<Stock> stocks = apiRequest.getStocks();
        return stocks.stream()
                .map(r -> {
                    Stock stock = stockService.getStockBySymbol(r.getSymbol());
                    stock.setVolume(r.getVolume());
                    return stock;
                })
                .collect(Collectors.toList());
    }

    private Double getTotalValue(List<Stock> response) {
        return response.stream()
                .mapToDouble(s -> {
                    s.setAssetValue(s.getVolume() * s.getLatestPrice());
                    return s.getAssetValue();
                })
                .sum();
    }

    private Double getSectorAssetValue(List<Stock> portfolios) {
        return portfolios.stream()
                .mapToDouble(Stock::getAssetValue)
                .sum();
    }

}
