package com.surakin.stock.counter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Portfolio {
    private String symbol;
    private String sector;
    private Integer volume;
    private Double latestPrice;
    private Double assetValue;
    private Double proportion;

    public Portfolio(String sector, Double assetValue, Double proportion) {
        this.sector = sector;
        this.assetValue = (new BigDecimal(assetValue).setScale(3, BigDecimal.ROUND_HALF_UP)).doubleValue();
        this.proportion = (new BigDecimal(proportion).setScale(3, BigDecimal.ROUND_HALF_UP)).doubleValue();
    }

    public Portfolio(Stock stock, Integer volume) {
        symbol = stock.getSymbol();
        sector = stock.getSector();
        this.volume = volume;
        latestPrice = stock.getLatestPrice();
        assetValue = volume * stock.getLatestPrice();
    }
}
