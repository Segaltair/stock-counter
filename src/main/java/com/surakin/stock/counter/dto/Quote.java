package com.surakin.stock.counter.dto;

import java.math.BigDecimal;

public class Quote {
    private BigDecimal latestPrice;
    private String sector;

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(BigDecimal latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
