package com.surakin.stock.counter.dto;

import lombok.Data;

@Data
public class Portfolio {
    private String symbol;
    private Integer volume;
    private Double latestPrice;
    private Double assetValue;

    public Portfolio(Stock stock, Integer volume) {
        symbol = stock.getSymbol();
        this.volume = volume;
        latestPrice = stock.getLatestPrice();
        assetValue = volume * stock.getLatestPrice();
    }
}
