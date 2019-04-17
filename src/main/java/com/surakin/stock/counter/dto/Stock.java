package com.surakin.stock.counter.dto;

import lombok.*;

@Data
public class Stock {

    private String symbol;
    private String companyName;
    private String sector;
    private String latestPrice;
    private Integer volume;

}
