package com.surakin.stock.counter.dto;

import lombok.Data;

@Data
public class Stock {

    private String symbol;
    private String companyName;
    private String sector;
    private Double latestPrice;
    private Integer volume;
    private Double assetValue;
}
