package com.surakin.stock.counter.controller;

import com.surakin.stock.counter.dto.ApiRequest;
import com.surakin.stock.counter.dto.Stock;
import com.surakin.stock.counter.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PortfolioController {

    @Autowired
    StockService stockService;

    @RequestMapping(value = "/portfolio", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Stock[]> baseUrlRedirect(@RequestBody ApiRequest apiRequest) {
        Stock[] request = apiRequest.getStocks();
        Stock[] response = new Stock[request.length];
        for (int i = 0; i < request.length; i++) {
            response[i] = stockService.getStockBySymbol(request[i].getSymbol());
        }

        return ResponseEntity.ok(response);
    }

}
