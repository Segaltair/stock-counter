package com.surakin.stock.counter.controller;

import com.surakin.stock.counter.dto.ApiRequest;
import com.surakin.stock.counter.dto.ApiResponse;
import com.surakin.stock.counter.dto.Portfolio;
import com.surakin.stock.counter.dto.Stock;
import com.surakin.stock.counter.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class PortfolioController {

    @Autowired
    StockService stockService;

    @RequestMapping(value = "/portfolio", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ApiResponse> baseUrlRedirect(@RequestBody ApiRequest apiRequest) {

        //Получить акции
        Portfolio[] response = Arrays.stream(apiRequest.getStocks())
                .map(r -> {
                    Stock stock = stockService.getStockBySymbol(r.getSymbol());
                    return new Portfolio(stock, r.getVolume());
                })
                .toArray(Portfolio[]::new);

        ApiResponse apiResponse = new ApiResponse(response);

        List<Portfolio> portfolios1 = new ArrayList<>();

        //Посчитать assetValues, sum(assetValues), proportion
        Arrays.stream(response)
                .collect(Collectors.groupingBy(Portfolio::getSector))
                .forEach((s, portfolios) -> {
                    Double sumAssetValue = portfolios.stream()
                            .mapToDouble(Portfolio::getAssetValue)
                            .sum();
                    portfolios1.add(new Portfolio(s, sumAssetValue, sumAssetValue/apiResponse.getValue()));
                });

        apiResponse.setAllocations(portfolios1.toArray(new Portfolio[0]));

        return ResponseEntity.ok(apiResponse);
    }

}
