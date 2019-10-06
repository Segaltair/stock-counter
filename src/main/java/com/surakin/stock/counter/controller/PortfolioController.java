package com.surakin.stock.counter.controller;

import com.surakin.stock.counter.dto.Request;
import com.surakin.stock.counter.dto.Response;
import com.surakin.stock.counter.service.PortfolioService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping(value = "/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response portfolio(@RequestBody Request request) {
        return portfolioService.getPortfolio(request);
    }
}
