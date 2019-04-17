package com.surakin.stock.counter.dto;

import lombok.Data;

@Data
public class ApiRequest {

    private StockRequest[] stocks;

}
