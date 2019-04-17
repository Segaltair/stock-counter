package com.surakin.stock.counter.dto;

import lombok.Data;

@Data
public class StockRequest {
    private String symbol;
    private Integer volume;
}
