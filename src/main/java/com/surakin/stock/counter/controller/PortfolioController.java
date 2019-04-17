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

import java.util.Arrays;

@Controller
public class PortfolioController {

    @Autowired
    StockService stockService;

    @RequestMapping(value = "/portfolio", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ApiResponse> baseUrlRedirect(@RequestBody ApiRequest apiRequest) {

        Portfolio[] response = Arrays.stream(apiRequest.getStocks())
                .map(r -> {
                    Stock stock = stockService.getStockBySymbol(r.getSymbol());
                    return new Portfolio(stock, r.getVolume());
                })
                .toArray(Portfolio[]::new);

        return ResponseEntity.ok(new ApiResponse(response));
    }

}
