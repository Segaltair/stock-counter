package com.surakin.stock.counter.service;

import com.surakin.stock.counter.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {
    private final StockInfoService stockInfoService;

    public PortfolioService(StockInfoService stockInfoService) {
        this.stockInfoService = stockInfoService;
    }

    public Response getPortfolio(Request request) {
        List<Portfolio> list = new ArrayList<>();
        BigDecimal totalValue = BigDecimal.ZERO;
        Map<String, BigDecimal> allocations = new HashMap<>();

        for (Stock stock : request.getStocks()) {
            Quote quote = stockInfoService.getQuoteBySymbol(stock.getSymbol());
            String sector = quote.getSector();
            BigDecimal assetValue = quote.getLatestPrice().multiply(BigDecimal.valueOf(stock.getVolume()));
            totalValue = totalValue.add(assetValue);
            if (allocations.get(sector) == null) {
                allocations.put(sector, assetValue);
            } else {
                allocations.put(sector, allocations.get(sector).add(assetValue));
            }
        }

        BigDecimal finalTotalValue = totalValue;
        allocations.forEach((sector, assetValue) -> {
            BigDecimal proportion = assetValue.divide(finalTotalValue, 3, RoundingMode.HALF_UP);
            list.add(new Portfolio(sector, assetValue.setScale(0, RoundingMode.HALF_UP), proportion));
        });

        return new Response(totalValue.toBigInteger().intValue(), list);
    }
}
