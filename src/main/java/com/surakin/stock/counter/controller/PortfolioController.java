package com.surakin.stock.counter.controller;

import com.surakin.stock.counter.dto.Request;
import com.surakin.stock.counter.dto.Response;
import com.surakin.stock.counter.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для подсчета текущей стоимости портфеля
 */
@RestController
@Slf4j
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Подсчитать текущую стоимость портфеля
     *
     * @param request список акций
     * @return общая стоимость и распределение акций по секторам
     */
    @PostMapping(value = "/portfolio")
    public Response portfolio(@RequestBody Request request) {
        log.info("Получен запрос на подсчет портфеля для {} акций", request.getStocks().size());
        return portfolioService.getPortfolio(request);
    }
}
